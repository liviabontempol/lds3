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
public class Vantagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int valor;
    private String descricao;
    private String foto;

    public Vantagem(int valor, String descricao, String foto) {
        this.valor = valor;
        this.descricao = descricao;
        this.foto = foto;
    }
}
