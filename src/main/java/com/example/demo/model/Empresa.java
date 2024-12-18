package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa extends User {
    
    private String cnpj;

    @OneToMany
    private List<Vantagem> vantagens;

    public Empresa(String nome, String email, String password, String cnpj, String endereco) {
        super( nome, email, password, endereco);
        this.cnpj = cnpj;
        this.role = UserRole.USER;
        this.vantagens = new ArrayList<>();
    }

    public void addVantagens(Vantagem vantagem){
        vantagens.add(vantagem);
    }

}
