package com.example.usuariocrud.exeception;

public class ErrorLogicaServicioUsuarioException extends Exception {

    public ErrorLogicaServicioUsuarioException() {
        super("Algo salió mal. Por favor, inténtelo de nuevo más tarde!");
    }

}
