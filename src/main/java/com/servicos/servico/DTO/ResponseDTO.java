package com.servicos.servico.DTO;

import java.sql.Date;

import com.servicos.servico.Enum.CategoriaServico;

public record  ResponseDTO (
    long id,
    CategoriaServico categoriaServico,
    Date dataDeCriacaoDoServico,
    boolean isServicoConcluido,
    double valor,
    String descricaoDoServico
) {}
    

