package br.com.fiap.revisaocp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.fiap.revisaocp2.dto.request.AlunoRequest;
import br.com.fiap.revisaocp2.dto.response.AlunoResponse;
import br.com.fiap.revisaocp2.entity.Aluno;
import br.com.fiap.revisaocp2.repository.AlunoRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class AlunoService implements ServiceDTO<Aluno, AlunoRequest, AlunoResponse> {

    @Autowired
    private AlunoRepository repo;

    @Override
    public Aluno toEntity(@NotNull AlunoRequest r) {
        return Aluno.builder()
                .senha(r.senha())
                .nome(r.nome())
                .email(r.email())
                .cpf(r.cpf())
                .build();
    }

    @Override
    public AlunoResponse toResponse(Aluno e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toResponse'");
    }

    @Override
    public List<Aluno> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Aluno> findAll(Example<Aluno> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Aluno finById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finById'");
    }

    @Override
    public Aluno save(Aluno e) {
        return repo.save(e);
    }

}
