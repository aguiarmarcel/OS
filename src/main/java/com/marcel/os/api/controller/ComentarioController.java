package com.marcel.os.api.controller;

import com.marcel.os.api.model.ComentarioInput;
import com.marcel.os.api.model.ComentarioModel;
import com.marcel.os.api.model.OrdemServicoInput;
import com.marcel.os.api.model.OrdemServicoModel;
import com.marcel.os.domain.exception.EntidadeNaoEncontradaException;
import com.marcel.os.domain.model.Comentario;
import com.marcel.os.domain.model.OrdemServico;
import com.marcel.os.domain.repository.OrdemServicoRepository;
import com.marcel.os.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
                                     @Valid @RequestBody ComentarioInput comentarioInput){
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId
                , comentarioInput.getDescricao());
        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de SErviço não encontrada."));
        return toCollectionModel(ordemServico.getComentarios());
    }

    //Método para a conversão de Comentario para ComentarioModel
    private ComentarioModel toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    //Método para passar uma lista de comentarios da ordemServico para uma lista de comentarioModel
    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios){
        return comentarios.stream().map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
