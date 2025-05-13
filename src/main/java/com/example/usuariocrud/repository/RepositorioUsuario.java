package com.example.usuariocrud.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuariocrud.model.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findByUsuario(String nombreUsuario);

    // Para buscar entre fechas
    List<Usuario> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Para ordenar por fecha descendente
    List<Usuario> findAllByOrderByFechaCreacionDesc();
}
