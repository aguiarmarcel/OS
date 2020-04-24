package com.marcel.os.api.controller;

import com.marcel.os.domain.model.Cliente;
import com.marcel.os.domain.model.OrdemServico;
import com.marcel.os.domain.repository.OrdemServicoRepository;
import com.marcel.os.domain.service.GestaoOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico){

        return gestaoOrdemServicoService.criar(ordemServico);
    }
}
