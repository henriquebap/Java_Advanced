package com.exemplo.restaurante_reviews.controller;

import com.exemplo.restaurantereviews.entity.Avaliacao;
import com.exemplo.restaurantereviews.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping
    public Page<Avaliacao> listar(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable);
    }

    @PostMapping
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> obterAvaliacao(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id,
            @RequestBody Avaliacao avaliacaoAtualizada) {
        return avaliacaoRepository.findById(id).map(avaliacao -> {
            avaliacao.setNota(avaliacaoAtualizada.getNota());
            avaliacao.setComentario(avaliacaoAtualizada.getComentario());
            Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
            return ResponseEntity.ok(avaliacaoSalva);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        return avaliacaoRepository.findById(id).map(avaliacao -> {
            avaliacaoRepository.delete(avaliacao);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}