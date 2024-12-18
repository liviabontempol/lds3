package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeAluno;
    private String nomeProfessor;
    private int valor;
    
    public Transacao(String nomeAluno, String nomeProfessor, int valor) {
        this.nomeAluno = nomeAluno;
        this.nomeProfessor = nomeProfessor;
        this.valor = valor;
    }

}