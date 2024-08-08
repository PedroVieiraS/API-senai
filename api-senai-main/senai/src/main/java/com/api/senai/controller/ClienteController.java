package com.api.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.senai.classes.Cliente;
import com.api.senai.dto.ClienteDTO;
import com.api.senai.dto.ClienteUpdateDTO;
import com.api.senai.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Buscar todos os clientes - getAll
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    // Buscar todos os clientes ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<Cliente>> getAllAtivos() {
        List<Cliente> clientes = clienteService.getAllAtivos();
        return ResponseEntity.ok(clientes);
    }
    
    // Buscar um cliente por id - getById
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<ClienteDTO>> getClientesDTO() {
        return ResponseEntity.ok(clienteService.getClientesDTO());
    }

    // Criar um cliente - create
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.create(cliente);
        
        return ResponseEntity.ok(clienteSalvo);
    }

    // Atualizar um cliente - update
    // Combinação do getById e create
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente clienteNovo) {
        Cliente clienteExistente = clienteService.getById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        Cliente clienteSalvo = clienteService.update(id, clienteExistente, clienteNovo);

        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/dto/{id}")
    public ResponseEntity<ClienteUpdateDTO> updateDTO (@PathVariable Long id, @RequestBody ClienteUpdateDTO clienteNovo) {
        Cliente clienteExistente = clienteService.getById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        ClienteUpdateDTO clienteDTO = clienteService.updateDTO(clienteExistente, clienteNovo);

        return ResponseEntity.ok(clienteDTO);

    }

    // Deletar um cliente - delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteService.delete(id));
    }
    
}
