package com.servicos.servico.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicos.servico.Model.Servico;
import com.servicos.servico.Repository.ServicoRepository;

@Service
public class ServicoService {
    
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }

    public Servico buscarServicoPorId(Long id){
        return servicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

   /*  public Servico salvaServico(RequestDTO dto){

        Servico servico = new Servico();
        servico.setCategoriaServico(dto.categoriaServico());
        servico.setAtendente(dto.atendente());
        servico.setValor(dto.valor());
        servico.setDescricaoDoServico(dto.descricaoDoServico());
        servico.setDataDeCriacaoDoServico(dto.dataDeCriacaoDoServico());          
        servico.setIsServicoConcluido(false);

        Servico salvo = servicoRepository.save(servico);

        return new ResponseDTO(

            salvo.getId(),
            salvo.getCategoriaServico(),
            salvo.getValor(),
            salvo.get
        );
    */
    }


