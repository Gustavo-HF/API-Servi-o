package com.servicos.servico.DTO;

import java.sql.Date;

public record RequestDTO (
    
    Enum categoriaServico,
    double valor,
    Enum atendente,
    String descricaoDoServico,
    Date dataDeCriacaoDoServico


) {}

