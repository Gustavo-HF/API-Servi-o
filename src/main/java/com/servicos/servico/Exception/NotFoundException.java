package com.servicos.servico.Exception;

public class NotFoundException extends RequestException {

    public NotFoundException() {
        super("NOT_FOUND", "not found");
    }

    public NotFoundException(String message) {
        super("NOT_FOUND", message);
    }
}
