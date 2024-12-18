package com.example.demo.controller;

import com.example.demo.model.dto.TransferenciaDTO;
import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping("transferir-pontos")
    public ResponseEntity<?> transferirPontos(@RequestBody TransferenciaDTO transferenciaDTO) {
        String emailProfessor = transferenciaDTO.emailProfessor();
        String emailAluno = transferenciaDTO.emailAluno();
        int valor = transferenciaDTO.valor();

        try {
            int saldoRestante = professorService.depositarSaldo(emailProfessor, emailAluno, valor);
            return ResponseEntity.ok("Pontos transferidos com sucesso! Saldo restante: " + saldoRestante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
