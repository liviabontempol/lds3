package com.example.demo.model.dto;

public record RegisterAlunoDTO(
    String nome,
    String email,
    String password,
    String curso,
    String endereco,
    String emailInstituicao,
    String cpf,
    String rg

) {
}
