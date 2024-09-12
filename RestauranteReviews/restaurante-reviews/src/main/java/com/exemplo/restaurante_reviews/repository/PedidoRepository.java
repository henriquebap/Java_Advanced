package com.exemplo.restaurante_reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.restaurante_reviews.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
