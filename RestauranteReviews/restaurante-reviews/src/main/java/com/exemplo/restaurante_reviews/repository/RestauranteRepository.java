package com.exemplo.restaurante_reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.restaurante_reviews.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
