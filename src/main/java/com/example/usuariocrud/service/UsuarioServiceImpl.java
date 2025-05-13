package com.example.usuariocrud.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.usuariocrud.dto.ApiRespuestaDto;
import com.example.usuariocrud.dto.ApiRespuestaEstado;
import com.example.usuariocrud.dto.RegistracionUsuarioDto;
import com.example.usuariocrud.exeception.ErrorLogicaServicioUsuarioException;
import com.example.usuariocrud.exeception.UsuarioNoEncontradoException;
import com.example.usuariocrud.exeception.UsuarioYaExisteException;
import com.example.usuariocrud.model.Usuario;
import com.example.usuariocrud.repository.RepositorioUsuario;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public ResponseEntity<ApiRespuestaDto> registrarUsuario(RegistracionUsuarioDto nuevosDetallesUsuario)
            throws UsuarioYaExisteException, ErrorLogicaServicioUsuarioException {

        try {
            if (repositorioUsuario.findByEmail(nuevosDetallesUsuario.getEmail()) != null) {
                throw new UsuarioYaExisteException(
                        "Registro fallido: Ya existe un usuario con el email " + nuevosDetallesUsuario.getEmail());
            }
            if (repositorioUsuario.findByUsuario(nuevosDetallesUsuario.getUsuario()) != null) {
                throw new UsuarioYaExisteException(
                        "Registro fallido: Ya existe un usuario con el nombre " + nuevosDetallesUsuario.getUsuario());
            }

            // resto del código sin cambios
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsuario(nuevosDetallesUsuario.getUsuario());
            nuevoUsuario.setEmail(nuevosDetallesUsuario.getEmail());
            nuevoUsuario.setFechaCreacion(LocalDateTime.now());

            repositorioUsuario.save(nuevoUsuario);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiRespuestaDto(ApiRespuestaEstado.EXITO.name(),
                            "¡La nueva cuenta de usuario ha sido creada exitosamente!"));

        } catch (UsuarioYaExisteException e) {
            throw new UsuarioYaExisteException(e.getMessage());
        } catch (Exception e) {
            log.error("Error al crear nueva cuenta de usuario: " + e.getMessage());
            throw new ErrorLogicaServicioUsuarioException();
        }
    }

    @Override
    public ResponseEntity<ApiRespuestaDto> obtenerTodosLosUsuarios() throws ErrorLogicaServicioUsuarioException {
        try {
            List<Usuario> usuarios = repositorioUsuario.findAll();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiRespuestaDto(ApiRespuestaEstado.EXITO.name(), usuarios.toString()));

        } catch (Exception e) {
            log.error("Error al obtener todos los usuarios: " + e.getMessage());
            throw new ErrorLogicaServicioUsuarioException();
        }
    }

    @Override
    public ResponseEntity<ApiRespuestaDto> actualizarUsuario(RegistracionUsuarioDto nuevosDetallesUsuario, long id)
            throws UsuarioNoEncontradoException, ErrorLogicaServicioUsuarioException {
        try {
            Usuario usuario = repositorioUsuario.findById(id)
                    .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con id " + id));

            usuario.setEmail(nuevosDetallesUsuario.getEmail());
            usuario.setUsuario(nuevosDetallesUsuario.getUsuario());

            repositorioUsuario.save(usuario);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiRespuestaDto(ApiRespuestaEstado.EXITO.name(),
                            "¡La cuenta de usuario ha sido actualizada exitosamente!"));

        } catch (UsuarioNoEncontradoException e) {
            throw new UsuarioNoEncontradoException(e.getMessage());
        } catch (Exception e) {
            log.error("Error al actualizar cuenta de usuario: " + e.getMessage());
            throw new ErrorLogicaServicioUsuarioException();
        }
    }

    @Override
    public ResponseEntity<ApiRespuestaDto> eliminarUsuario(long id)
            throws ErrorLogicaServicioUsuarioException, UsuarioNoEncontradoException {
        try {
            Usuario usuario = repositorioUsuario.findById(id)
                    .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con id " + id));

            repositorioUsuario.delete(usuario);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiRespuestaDto(ApiRespuestaEstado.EXITO.name(),
                            "¡La cuenta de usuario ha sido eliminada exitosamente!"));
        } catch (UsuarioNoEncontradoException e) {
            throw new UsuarioNoEncontradoException(e.getMessage());
        } catch (Exception e) {
            log.error("Error al eliminar cuenta de usuario: " + e.getMessage());
            throw new ErrorLogicaServicioUsuarioException();
        }
    }
}
