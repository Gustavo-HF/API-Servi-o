package com.servicos.servico.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicos.servico.DTO.RequestDTO;
import com.servicos.servico.DTO.ResponseDTO;
import com.servicos.servico.Model.Servico;
import com.servicos.servico.Service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.ok(servicoService.listarServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Servico servico = servicoService.buscarServicoPorId(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> salvarServico(@RequestBody RequestDTO dto) {
        ResponseDTO salvo = servicoService.salvaServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> atualizarServico(@PathVariable Long id,
            @RequestBody RequestDTO dto) {

        ResponseDTO atualizado = servicoService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarporId(@PathVariable Long id) {

        servicoService.excluirServicoPorId(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Serviço deletado com sucesso");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Map<String, String>> deletarConcluidos(){
        servicoService.deletarServicosConcluidos();

        Map<String, String> response = new HashMap<>();
        response.put("Mensagem:", "Serviços concluídos deletados com sucesso");

        return ResponseEntity.ok(response);
    }

}
