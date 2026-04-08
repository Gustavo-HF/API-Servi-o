package com.servicos.servico.Exception;

public class InvalidDateRangeException extends RequestException {

    public InvalidDateRangeException() {
        super("INVALID_DATE_RANGE", "A data de início não pode ser depois da de finalização");
    }

    public InvalidDateRangeException(String message) {
        super("INVALID_DATE_RANGE", message);
    }
}
