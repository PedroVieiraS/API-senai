package com.api.senai.classes;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(length = 11)
    private String telefone;

    @Column(unique = true)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cliente_ativo")
    private boolean clienteAtivo = true;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;
}

// DTO = Data Tranfer Object
