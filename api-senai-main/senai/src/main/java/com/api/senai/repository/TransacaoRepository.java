package com.api.senai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.senai.classes.ContaBancaria;
import com.api.senai.classes.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaOrigemOrContaDestinoOrderByDataDesc(
        ContaBancaria contaOrigem, 
        ContaBancaria contaDestino
    );

}
