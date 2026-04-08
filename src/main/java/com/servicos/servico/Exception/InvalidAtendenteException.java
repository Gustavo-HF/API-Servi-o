package com.servicos.servico.Exception;

public class InvalidAtendenteException extends RequestException {

    public InvalidAtendenteException() {
        super("INVALID_ATENDENTE", "Que é obrigatório ter um agendamento com um dos atendentes existentes");
    }

    public InvalidAtendenteException(String message) {
        super("INVALID_ATENDENTE", message);
    }
}
