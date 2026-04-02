package com.servicos.servico.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/id")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id)  {
        Servico servico = servicoService.buscarServicoPorId(id);

        if(servico == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    
    
    
}
