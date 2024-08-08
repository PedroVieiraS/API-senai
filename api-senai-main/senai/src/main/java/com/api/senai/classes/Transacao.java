package com.api.senai.classes;

import java.time.LocalDateTime;

import com.api.senai.enums.TipoTransacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double valor;

    // Tipo de transação é um ENUM
    @Column(nullable = false)
    @Enumerated
    private TipoTransacao tipoTransacao;

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "conta_origem_id", referencedColumnName = "numeroConta")
    private ContaBancaria contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id", referencedColumnName = "numeroConta")
    private ContaBancaria contaDestino;

}
