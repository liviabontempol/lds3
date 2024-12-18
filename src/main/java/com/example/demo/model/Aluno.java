package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno extends User {
    protected String curso;

    protected String emailInstituicao;


    protected String cpf;

    protected String rg;

    @OneToMany
    protected List<Transacao> transacaoList;

    @OneToMany
    private List<Vantagem> vantagens;

    protected int saldo;

    public Aluno(String nome, String email, String password, String curso, String instituicao,
            String endereco, String cpf, String rg) {
        super(nome, email, password, endereco);
        this.curso = curso;
        this.emailInstituicao = instituicao;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.role = UserRole.USER;
        this.transacaoList = new ArrayList<>();
    }

    public void addTransacao(Transacao transacao){
        transacaoList.add(transacao);
    }

    public void addVantagens(Vantagem vantagem){
        vantagens.add(vantagem);
    }
  
}
