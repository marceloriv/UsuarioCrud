package com.example.usuariocrud.exeception;

public class UsuarioYaExisteException extends Exception {

    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }

}
