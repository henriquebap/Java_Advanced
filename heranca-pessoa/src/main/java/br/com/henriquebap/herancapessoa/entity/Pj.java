package br.com.henriquebap.herancapessoa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="TB_PJ")
public class Pj extends Pessoa {

    private String cnpj;
    
}
