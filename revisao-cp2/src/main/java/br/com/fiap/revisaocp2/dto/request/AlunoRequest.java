package br.com.fiap.revisaocp2.dto.request;

//import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AlunoRequest(

        @Size(min = 3, max = 255) @NotNull(message = "Nome obrigatorio") String nome,

        // @CPF(message = "CPF E INVALIDO")
        @NotNull(message = "cpf e obrigatorio") String cpf,

        @NotNull(message = "Email e obrigatorio") @Email(message = "Email invalido") String email,

        @NotNull(message = "Senha e obrigatorio") @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}") String senha

) {

}
