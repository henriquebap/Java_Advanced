package com.exemplo.restaurante_reviews.repository;

import com.exemplo.restaurantereviews.entity.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Page<Restaurante> findByNomeContaining(String nome, Pageable pageable);
}