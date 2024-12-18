package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends User {

    private int saldo;
    private String instituicao;

    @OneToMany
    private List<Transacao> transacaoList;


    public Professor(String nome, String email, String password, String endereco, String instituicao) {
        super(nome, email, password, endereco);
        this.instituicao = instituicao;
        this.saldo = 1000;
        this.role = UserRole.USER;
    }



    public void addTransacao(Transacao transacao){
        transacaoList.add(transacao);
    }
}
