package com.marcel.os.domain.service;

import com.marcel.os.domain.exception.NegocioException;
import com.marcel.os.domain.model.Cliente;
import com.marcel.os.domain.model.OrdemServico;
import com.marcel.os.domain.model.StatusOrdemServico;
import com.marcel.os.domain.repository.ClienteRepository;
import com.marcel.os.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado."));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void excluir(Long ordemServicoId){

        ordemServicoRepository.deleteById(ordemServicoId);
    }
}
