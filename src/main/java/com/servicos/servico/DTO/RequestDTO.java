package com.servicos.servico.DTO;

import java.sql.Date;
import com.servicos.servico.Enum.CategoriaServico;
import com.servicos.servico.Enum.Atendente;

public record RequestDTO (
    
    CategoriaServico categoriaServico,
    double valor,
    Atendente atendente,
    String descricaoDoServico,
    Date dataDeCriacaoDoServico


) {}

