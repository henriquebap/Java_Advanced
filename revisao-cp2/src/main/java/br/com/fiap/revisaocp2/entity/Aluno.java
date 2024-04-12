package br.com.fiap.revisaocp2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "TB_ALUNO_JAVA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CPF", columnNames = "CPF_ALUNO"),
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL_ALUNO")
})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ALUNO")
    @SequenceGenerator(name = "SQ_ALUNO", sequenceName = "SQ_ALUNO", allocationSize = 1)
    @Column(name = "ID_ALUNO")
    private Long id;

    @Column(name = "NM_ALUNO")
    private String nome;

    @Column(name = "CPF_ALUNO")
    private String cpf;

    @Column(name = "EMAIL_ALUNO")
    private String email;

    @Column(name = "SENHA_ALUNO")
    private String senha;

}
