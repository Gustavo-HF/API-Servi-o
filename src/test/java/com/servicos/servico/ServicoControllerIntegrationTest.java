package com.servicos.servico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.servicos.servico.Enum.Atendente;
import com.servicos.servico.Enum.CategoriaServico;
import com.servicos.servico.Model.Servico;
import com.servicos.servico.Repository.ServicoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServicoControllerIntegrationTest {

    // Porta aleatória usada pelo servidor de teste
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ServicoRepository servicoRepository;

    @AfterEach
    void cleanup() {
        // Limpa os registros entre cada execução de teste
        servicoRepository.deleteAll();
    }

    @Test
    void deveCriarServicoEListarEndpointsDoController() {
        // POST /servicos para criar um novo serviço
        Map<String, Object> request = buildRequest("Cabelo", 120.0, "Pedro", "Corte masculino", "2024-01-01");

        ResponseEntity<Map> createResponse = postServico(request);

        // Validação do status HTTP e do corpo de criação
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).containsEntry("categoriaServico", "Cabelo");
        assertThat(createResponse.getBody()).containsEntry("valor", 120.0);
        assertThat(createResponse.getBody()).containsEntry("descricaoDoServico", "Corte masculino");

        Long id = Long.valueOf(createResponse.getBody().get("id").toString());

        // GET /servicos para listar todos os serviços criados
        ResponseEntity<List> listResponse = restTemplate.getForEntity(url("/servicos"), List.class);

        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).hasSize(1);

        Map<?, ?> savedServico = (Map<?, ?>) listResponse.getBody().get(0);
        assertThat(savedServico.get("id")).isEqualTo(id.intValue());
    }

    @Test
    void deveBuscarEAtualizarServicoPorId() {
        // Cria um serviço para testar GET e PUT
        Map<String, Object> request = buildRequest("Barba", 50.5, "Joao", "Barba modelada", "2024-02-01");
        Long id = Long.valueOf(postServico(request).getBody().get("id").toString());

        // PUT /servicos/{id} para atualizar o serviço criado
        Map<String, Object> updateRequest = buildRequest("Sobrancelha", 75.0, "Carlos", "Design completo", "2024-02-02");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Map> updateResponse = restTemplate.exchange(
                url("/servicos/" + id),
                org.springframework.http.HttpMethod.PUT,
                new HttpEntity<>(updateRequest, headers),
                Map.class);

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateResponse.getBody()).containsEntry("id", id.intValue());
        assertThat(updateResponse.getBody()).containsEntry("categoriaServico", "Sobrancelha");
        assertThat(updateResponse.getBody()).containsEntry("descricaoDoServico", "Design completo");
    }

    @Test
    void deveDeletarServicoPorId() {
        // Cria um serviço e depois executa DELETE /servicos/{id}
        Long id = Long.valueOf(postServico(buildRequest("Cabelo", 100.0, "Pedro", "Corte simples", "2024-03-01")).getBody().get("id").toString());

        ResponseEntity<Map> deleteResponse = restTemplate.exchange(
                url("/servicos/" + id),
                org.springframework.http.HttpMethod.DELETE,
                null,
                Map.class);

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleteResponse.getBody()).containsEntry("mensagem", "Serviço deletado com sucesso");
        assertThat(servicoRepository.existsById(id)).isFalse();
    }

    @Test
    void deveDeletarServicosConcluidos() {
        // Cria um serviço concluído para testar DELETE /servicos/all
        Servico concluido = new Servico();
        concluido.setCategoriaServico(CategoriaServico.Barba);
        concluido.setValor(55.0);
        concluido.setAtendente(Atendente.Joao);
        concluido.setDescricaoDoServico("Barba finalizada");
        concluido.setDataDeCriacaoDoServico(java.sql.Date.valueOf("2024-03-10"));
        concluido.setIsServicoConcluido(true);
        servicoRepository.save(concluido);

        ResponseEntity<Map> response = restTemplate.exchange(
                url("/servicos/all"),
                org.springframework.http.HttpMethod.DELETE,
                null,
                Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("Mensagem:", "Serviços concluídos deletados com sucesso");
        assertThat(servicoRepository.findAll()).isEmpty();
    }

    // Métodos auxiliares usados pelos testes para montar requests e chamar a API
    private Map<String, Object> buildRequest(String categoriaServico, double valor, String atendente, String descricao, String data) {
        Map<String, Object> request = new HashMap<>();
        request.put("categoriaServico", categoriaServico);
        request.put("valor", valor);
        request.put("atendente", atendente);
        request.put("descricaoDoServico", descricao);
        request.put("dataDeCriacaoDoServico", data);
        return request;
    }

    private ResponseEntity<Map> postServico(Map<String, Object> request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(url("/servicos"), new HttpEntity<>(request, headers), Map.class);
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }
}
