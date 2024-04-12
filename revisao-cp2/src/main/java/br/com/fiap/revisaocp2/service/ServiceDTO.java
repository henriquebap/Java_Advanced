package br.com.fiap.revisaocp2.service;

import java.util.List;

import org.springframework.data.domain.Example;

public interface ServiceDTO<Entity, Request, Response> {

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    List<Entity> findAll();

    List<Entity> findAll(Example<Entity> example);

    Entity finById(Long id);

    Entity save(Entity e);

}
