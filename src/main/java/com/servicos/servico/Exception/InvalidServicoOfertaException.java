package com.servicos.servico.Exception;

public class InvalidServicoOfertaException extends RequestException {

    public InvalidServicoOfertaException() {
        super("INVALID_SERVICO_OFERTA", "E com os serviços que a barbearia oferece.");
    }

    public InvalidServicoOfertaException(String message) {
        super("INVALID_SERVICO_OFERTA", message);
    }
}
