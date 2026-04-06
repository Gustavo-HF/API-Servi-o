package com.servicos.servico.Exception;

public class RequestException  extends  RuntimeException{
    private final String errorCode;

    public RequestException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }
}
