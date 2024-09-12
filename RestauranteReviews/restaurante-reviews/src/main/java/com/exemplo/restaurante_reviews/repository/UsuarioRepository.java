package com.exemplo.restaurante_reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.restaurante_reviews.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
