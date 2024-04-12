package br.com.fiap.revisaocp2.dto.response;

import lombok.Builder;

@Builder
public record AlunoResponse(

        Long id,

        String nome,

        String cpf,

        String email

) {
}
