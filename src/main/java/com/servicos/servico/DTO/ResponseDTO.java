package com.servicos.servico.DTO;

import java.sql.Date;

public record  ResponseDTO (
    long id,
    Enum categoriaServico,
    Date dataDeCriacaoDoServico,
    boolean isServicoConcluido,
    double valor,
    String descricaoDoServico
) {}
    

