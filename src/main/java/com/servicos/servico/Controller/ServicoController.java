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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Operações de cadastro e gerenciamento de serviços")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Operation(
            summary = "Listar todos os serviços",
            description = "Retorna todos os serviços registrados na base.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de serviços",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Servico.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.ok(servicoService.listarServicos());
    }

    @Operation(
            summary = "Buscar serviço por ID",
            description = "Retorna o serviço correspondente ao ID informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servico.class))),
                    @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@Parameter(description = "ID do serviço", example = "1") @PathVariable Long id) {
        Servico servico = servicoService.buscarServicoPorId(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    @Operation(
            summary = "Criar novo serviço",
            description = "Cria um serviço com os dados fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do serviço a ser criado",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequestDTO.class),
                            examples = @ExampleObject(value = "{\"categoriaServico\":\"Cabelo\",\"valor\":120.0,\"atendente\":\"Pedro\",\"descricaoDoServico\":\"Corte masculino\",\"dataDeCriacaoDoServico\":\"2024-01-01\"}"))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> salvarServico(@RequestBody RequestDTO dto) {
        ResponseDTO salvo = servicoService.salvaServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(
            summary = "Atualizar serviço por ID",
            description = "Atualiza os dados do serviço informado pelo ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do serviço a serem atualizados",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequestDTO.class),
                            examples = @ExampleObject(value = "{\"categoriaServico\":\"Sobrancelha\",\"valor\":75.0,\"atendente\":\"Carlos\",\"descricaoDoServico\":\"Design completo\",\"dataDeCriacaoDoServico\":\"2024-02-02\"}"))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> atualizarServico(@Parameter(description = "ID do serviço", example = "1") @PathVariable Long id,
            @RequestBody RequestDTO dto) {

        ResponseDTO atualizado = servicoService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);

    }

    @Operation(
            summary = "Deletar serviço por ID",
            description = "Remove o serviço identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço deletado com sucesso",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarporId(@Parameter(description = "ID do serviço", example = "1") @PathVariable Long id) {

        servicoService.excluirServicoPorId(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Serviço deletado com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Deletar serviços concluídos",
            description = "Remove todos os serviços que foram marcados como concluídos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviços concluídos deletados com sucesso",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @DeleteMapping("/all")
    public ResponseEntity<Map<String, String>> deletarConcluidos(){
        servicoService.deletarServicosConcluidos();

        Map<String, String> response = new HashMap<>();
        response.put("Mensagem:", "Serviços concluídos deletados com sucesso");

        return ResponseEntity.ok(response);
    }

}
