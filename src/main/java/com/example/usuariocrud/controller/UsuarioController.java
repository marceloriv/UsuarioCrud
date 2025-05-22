package com.example.usuariocrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuariocrud.dto.ApiRespuestaDto;
import com.example.usuariocrud.dto.RegistracionUsuarioDto;
import com.example.usuariocrud.exeception.ErrorLogicaServicioUsuarioException;
import com.example.usuariocrud.exeception.UsuarioNoEncontradoException;
import com.example.usuariocrud.exeception.UsuarioYaExisteException;
import com.example.usuariocrud.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiRespuestaDto> registrarUsuario(@Valid @RequestBody RegistracionUsuarioDto registracionUsuarioDto)
            throws UsuarioYaExisteException, ErrorLogicaServicioUsuarioException {
        return usuarioService.registrarUsuario(registracionUsuarioDto);
    }

    @GetMapping
    public ResponseEntity<ApiRespuestaDto> obtenerTodosLosUsuarios()
            throws ErrorLogicaServicioUsuarioException {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ApiRespuestaDto> actualizarUsuario(
            @Valid @RequestBody RegistracionUsuarioDto registracionUsuarioDto,
            @PathVariable long id)
            throws UsuarioNoEncontradoException, ErrorLogicaServicioUsuarioException {
        return usuarioService.actualizarUsuario(registracionUsuarioDto, id);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiRespuestaDto> eliminarUsuario(@PathVariable long id)
            throws UsuarioNoEncontradoException, ErrorLogicaServicioUsuarioException {
        return usuarioService.eliminarUsuario(id);
    }
}
