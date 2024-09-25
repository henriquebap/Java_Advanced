package com.exemplo.restaurante_reviews.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "avaliacoes")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    private String comentario;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}