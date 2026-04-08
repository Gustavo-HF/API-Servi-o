package com.servicos.servico.DTO;

import java.sql.Date;
import com.servicos.servico.Enum.CategoriaServico;
import com.servicos.servico.Enum.Atendente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de requisição para criação e atualização de serviço")
public record RequestDTO (
    @Schema(description = "Categoria do serviço", example = "Cabelo", requiredMode = Schema.RequiredMode.REQUIRED)
    CategoriaServico categoriaServico,
    @Schema(description = "Valor do serviço", example = "120.0", requiredMode = Schema.RequiredMode.REQUIRED)
    double valor,
    @Schema(description = "Nome do atendente", example = "Pedro", requiredMode = Schema.RequiredMode.REQUIRED)
    Atendente atendente,
    @Schema(description = "Descrição do serviço", example = "Corte masculino", requiredMode = Schema.RequiredMode.REQUIRED)
    String descricaoDoServico,
    @Schema(description = "Data de criação do serviço", example = "2024-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    Date dataDeCriacaoDoServico

) {}

