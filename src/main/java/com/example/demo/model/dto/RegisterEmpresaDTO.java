package com.example.demo.model.dto;

public record RegisterEmpresaDTO(
    String nome,
   String email,
    String password,
    String cnpj,
    String endereco

) {
}
