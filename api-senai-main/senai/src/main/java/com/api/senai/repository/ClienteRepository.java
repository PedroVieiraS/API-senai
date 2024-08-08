package com.api.senai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.senai.classes.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método para buscar somente os clientes ativos (GetAllAtivos)
    List<Cliente> findByClienteAtivoTrue();

    @Query("SELECT new com.api.senai.dto.ClienteDTO(c.id, c.nome) FROM Cliente c")
    List<Cliente> getClientesDTO();

}
