package com.example.usuariocrud.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.usuariocrud.dto.ApiRespuestaDto;
import com.example.usuariocrud.dto.RegistracionUsuarioDto;
import com.example.usuariocrud.exeception.ErrorLogicaServicioUsuarioException;
import com.example.usuariocrud.exeception.UsuarioNoEncontradoException;
import com.example.usuariocrud.exeception.UsuarioYaExisteException;

@Service
public interface UsuarioService {

    ResponseEntity<ApiRespuestaDto> registrarUsuario(RegistracionUsuarioDto nuevosDetallesUsuario)
            throws UsuarioYaExisteException, ErrorLogicaServicioUsuarioException;

    ResponseEntity<ApiRespuestaDto> obtenerTodosLosUsuarios()
            throws ErrorLogicaServicioUsuarioException;

    ResponseEntity<ApiRespuestaDto> actualizarUsuario(RegistracionUsuarioDto nuevosDetallesUsuario, long id)
            throws UsuarioNoEncontradoException, ErrorLogicaServicioUsuarioException;

    ResponseEntity<ApiRespuestaDto> eliminarUsuario(long id)
            throws ErrorLogicaServicioUsuarioException, UsuarioNoEncontradoException;

    /*     ResponseEntity<ApiRespuestaDto<?>> eliminarUsuario(long id)
            throws ErrorLogicaServicioUsuarioException, UsuarioNoEncontradoException;
     */
}
