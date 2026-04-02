package com.servicos.servico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicos.servico.Model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
    

}
