package com.exemplo.restaurante_reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.restaurante_reviews.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}