package com.marcel.os.api.controller;

import com.marcel.os.domain.model.Cliente;
import com.marcel.os.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        //return clienteRepository.findAll();
        //return clienteRepository.findByNome("");
        return clienteRepository.findByNomeContaining("Edu");
    }
    @GetMapping("clientes/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId){
         Optional<Cliente> cliente = clienteRepository.findById(clienteId);
         return cliente.orElse(null);
    }
}
