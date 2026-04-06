package com.servicos.servico.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicos.servico.DTO.RequestDTO;
import com.servicos.servico.DTO.ResponseDTO;
import com.servicos.servico.Model.Servico;
import com.servicos.servico.Repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico buscarServicoPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public ResponseDTO salvaServico(RequestDTO dto) {

        Servico servico = new Servico();

        servico.setCategoriaServico(dto.categoriaServico());
        servico.setValor(dto.valor());
        servico.setAtendente(dto.atendente());
        servico.setDescricaoDoServico(dto.descricaoDoServico());

        servico.setDataDeCriacaoDoServico(dto.dataDeCriacaoDoServico());

        servico.setIsServicoConcluido(false);

        Servico salvo = servicoRepository.save(servico);

        return new ResponseDTO(salvo.getId(),
                salvo.getCategoriaServico(),
                salvo.getDataDeCriacaoDoServico(),
                salvo.isIsServicoConcluido(),
                salvo.getValor(),
                salvo.getDescricaoDoServico());
    }

    public ResponseDTO atualizar(Long id, RequestDTO dto) {

        Servico existe = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        existe.setCategoriaServico(dto.categoriaServico());
        existe.setValor(dto.valor());
        existe.setAtendente(dto.atendente());
        existe.setDescricaoDoServico(dto.descricaoDoServico());

        Servico atualizado = servicoRepository.save(existe);

        return new ResponseDTO(
                atualizado.getId(),
                atualizado.getCategoriaServico(),
                atualizado.getDataDeCriacaoDoServico(),
                atualizado.isIsServicoConcluido(),
                atualizado.getValor(),
                atualizado.getDescricaoDoServico()
        );
    }

    public Servico excluirServicoPorId(Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        servicoRepository.delete(servico);

        return servico;
    }

    public void deletarServicosConcluidos() {

        List<Servico> concluidos = servicoRepository.findAll()
                .stream()
                .filter(Servico::isIsServicoConcluido)
                .toList();

        if (concluidos.isEmpty()) {
            throw new RuntimeException("Não há nenhum serviço concluído para ser deletado");
        }

        servicoRepository.deleteAll(concluidos);

    }
}
