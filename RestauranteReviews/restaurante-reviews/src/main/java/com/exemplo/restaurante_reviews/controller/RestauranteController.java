package com.exemplo.restaurante_reviews.controller;

import com.exemplo.restaurantereviews.entity.Restaurante;
import com.exemplo.restaurantereviews.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public Page<Restaurante> listar(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return restauranteRepository.findAll(pageable);
    }

    @GetMapping("/buscar")
    public Page<Restaurante> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return restauranteRepository.findByNomeContaining(nome, pageable);
    }

    @PostMapping
    public Restaurante criar(@RequestBody Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> obterRestaurante(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id,
            @RequestBody Restaurante restauranteAtualizado) {
        return restauranteRepository.findById(id).map(restaurante -> {
            restaurante.setNome(restauranteAtualizado.getNome());
            restaurante.setEndereco(restauranteAtualizado.getEndereco());
            Restaurante restauranteSalvo = restauranteRepository.save(restaurante);
            return ResponseEntity.ok(restauranteSalvo);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        return restauranteRepository.findById(id).map(restaurante -> {
            restauranteRepository.delete(restaurante);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}