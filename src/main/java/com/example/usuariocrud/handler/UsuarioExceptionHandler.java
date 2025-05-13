package com.example.usuariocrud.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.usuariocrud.dto.ApiRespuestaDto;
import com.example.usuariocrud.dto.ApiRespuestaEstado;
import com.example.usuariocrud.exeception.ErrorLogicaServicioUsuarioException;
import com.example.usuariocrud.exeception.UsuarioNoEncontradoException;
import com.example.usuariocrud.exeception.UsuarioYaExisteException;

@RestControllerAdvice
public class UsuarioExceptionHandler {

    @ExceptionHandler(value = UsuarioNoEncontradoException.class)
    public ResponseEntity<ApiRespuestaDto> usuarioNoEncontradoExceptionHandler(UsuarioNoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiRespuestaDto(ApiRespuestaEstado.ERROR.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = UsuarioYaExisteException.class)
    public ResponseEntity<ApiRespuestaDto> usuarioYaExisteExceptionHandler(UsuarioYaExisteException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiRespuestaDto(ApiRespuestaEstado.ERROR.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ErrorLogicaServicioUsuarioException.class)
    public ResponseEntity<ApiRespuestaDto> errorLogicaServicioUsuarioExceptionHandler(
            ErrorLogicaServicioUsuarioException exception) {
        return ResponseEntity.badRequest()
                .body(new ApiRespuestaDto(ApiRespuestaEstado.ERROR.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRespuestaDto> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {

        List<String> mensajesError = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            mensajesError.add(error.getDefaultMessage());
        });

        return ResponseEntity.badRequest()
                .body(new ApiRespuestaDto(ApiRespuestaEstado.ERROR.name(), mensajesError.toString()));
    }
}
