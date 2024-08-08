package com.api.senai.exceptions;

public class ErroInesperado extends RuntimeException {

    private final String mensagem;

    public ErroInesperado(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return "Erro inesperado: " + mensagem;
    }

}
