package com.marcel.os.domain.repository;

import com.marcel.os.domain.model.Cliente;
import com.marcel.os.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
    Cliente findByEmail(String email);

    Cliente findById(OrdemServico ordemServico);
}
