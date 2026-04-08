package com.servicos.servico.DTO;

import java.sql.Date;

import com.servicos.servico.Enum.CategoriaServico;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta de serviço")
public record ResponseDTO (
    @Schema(description = "Identificador do serviço", example = "1")
    long id,
    @Schema(description = "Categoria do serviço", example = "Cabelo")
    CategoriaServico categoriaServico,
    @Schema(description = "Data de criação do serviço", example = "2024-01-01")
    Date dataDeCriacaoDoServico,
    @Schema(description = "Flag que indica se o serviço está concluído", example = "false")
    boolean isServicoConcluido,
    @Schema(description = "Valor do serviço", example = "120.0")
    double valor,
    @Schema(description = "Descrição do serviço", example = "Corte masculino")
    String descricaoDoServico
) {}
    

