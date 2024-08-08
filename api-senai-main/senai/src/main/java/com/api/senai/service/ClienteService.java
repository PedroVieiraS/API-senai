package com.api.senai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senai.classes.Cliente;
import com.api.senai.dto.ClienteDTO;
import com.api.senai.dto.ClienteUpdateDTO;
import com.api.senai.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                                .orElse(null);
    }

    public Cliente create(Cliente cliente) {
        // Adicionar tratamentos para garantir que a persistencia 
        // acontece com todos os dados necessários
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Disparar uma mensagem por email de detalhes do cliente
        emailService.sendEmailByJakartaMail(clienteSalvo);

        return clienteSalvo;
    }

    public Cliente update(Long id, Cliente clienteExistente, Cliente clienteNovo) {

        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getCpf() != null) {
            clienteExistente.setCpf(clienteNovo.getCpf());
        }
        if (clienteNovo.getEndereco() != null) {
            clienteExistente.setEndereco(clienteNovo.getEndereco());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }
        if (clienteNovo.getDataNascimento() != null) {
            clienteExistente.setDataNascimento(clienteNovo.getDataNascimento());
        }
        if (clienteNovo.isClienteAtivo() != clienteExistente.isClienteAtivo()) {
            clienteExistente.setClienteAtivo(clienteNovo.isClienteAtivo());
        }

        return clienteRepository.save(clienteExistente);
    }

    public Cliente delete(Long id) {
        // Delete anterior
        clienteRepository.deleteById(id);

        // Delete lógico
        Cliente cliente = getById(id);
        cliente.setClienteAtivo(false);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllAtivos() {
        return clienteRepository.findByClienteAtivoTrue();
    }

    public ClienteUpdateDTO updateDTO(Cliente clienteExistente, ClienteUpdateDTO clienteNovo) {

        // Converter o que é DTO pra Cliente

        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }

        // Atualizar o clienteExistente com os dados do clienteNovo
        Cliente clienteSalvo = clienteRepository.save(clienteExistente);

        // Converter o Cliente pra DTO 
        ClienteUpdateDTO clienteDTO = new ClienteUpdateDTO();
        clienteDTO.setId(clienteSalvo.getId());
        clienteDTO.setNome(clienteSalvo.getNome());
        clienteDTO.setTelefone(clienteSalvo.getTelefone());
        clienteDTO.setEmail(clienteSalvo.getEmail());

        // Retornar
        return clienteDTO;
    }

    public List<ClienteDTO> getClientesDTO() {

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());

            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }
}
