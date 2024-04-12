package br.com.fiap.revisaocp2.resource;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

public interface ResourceDTO<Request, Response> {

    ResponseEntity<Collection<Response>> findAll();

    ResponseEntity<Response> finById(Long id);

    ResponseEntity<Response> save(Request r);

}
