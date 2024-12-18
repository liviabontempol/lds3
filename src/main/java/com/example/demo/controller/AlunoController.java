package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Aluno;
import com.example.demo.model.Transacao;
import com.example.demo.model.Vantagem;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.service.AlunoService;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    AlunoService alunoService;
    
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> visualizarTodosOsAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> visualizarAluno(@PathVariable int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluno com ID " + id + " não encontrado.");
        }
    }

    @GetMapping("/aluno/{email}/transacoes")
    public ResponseEntity<?> relatorioTransacoes(@PathVariable String email) {
        try {
            List<Transacao> transacoes = alunoService.relatorioTransacoes(email);
            if (transacoes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Nenhuma transação encontrada para o aluno com email: " + email);
            }
            return ResponseEntity.ok(transacoes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping("/{email}/vantagens/{vantagemId}")
    public ResponseEntity<Vantagem> selecionarVantagem(
            @PathVariable String email, 
            @PathVariable int vantagemId) {
        try {
           Vantagem vantagem = alunoService.selecionarVantagem(email, vantagemId);
            return ResponseEntity.ok(vantagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    
}