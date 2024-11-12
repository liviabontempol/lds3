package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String email;

    private String cnpj;

    @OneToMany
    private List<Vantagem> vantagens;


    public List<Vantagem> getVantagens() {
        return vantagens;
    }

    public void setVantagens(List<Vantagem> vantagens) {
        this.vantagens = vantagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // public String[] getVantagensArray() {
    //     return vantagens != null ? vantagens.split(",") : new String[0];
    // }
    // public void setVantagensArray(String[] vantagensArray) {
    //     this.vantagens = String.join(",", vantagensArray);
    // }

}
