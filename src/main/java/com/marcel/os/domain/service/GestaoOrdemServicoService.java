package com.marcel.os.domain.service;

import com.marcel.os.domain.exception.EntidadeNaoEncontradaException;
import com.marcel.os.domain.exception.NegocioException;
import com.marcel.os.domain.model.Cliente;
import com.marcel.os.domain.model.Comentario;
import com.marcel.os.domain.model.OrdemServico;
import com.marcel.os.domain.model.StatusOrdemServico;
import com.marcel.os.domain.repository.ClienteRepository;
import com.marcel.os.domain.repository.ComentarioRepository;
import com.marcel.os.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado."));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void excluir(Long ordemServicoId){

        ordemServicoRepository.deleteById(ordemServicoId);
    }

    public void finalizar(Long ordemServicoId){
        OrdemServico ordemServico = buscar(ordemServicoId);
        ordemServico.finalizar();
        ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao){

        OrdemServico ordemServico = buscar(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    //Método usado em dois outros métodos. Então, crei esse
    // para ser usado nos outros dois e não repetir o código.
    private OrdemServico buscar(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada."));
    }
}
