package com.servicos.servico.Exception;

public class InvalidServicoConcluidoException extends RequestException {

    public InvalidServicoConcluidoException() {
        super("INVALID_SERVICO_CONCLUIDO", "Que o serviço deve estar marcado como concluído ou não");
    }

    public InvalidServicoConcluidoException(String message) {
        super("INVALID_SERVICO_CONCLUIDO", message);
    }
}
