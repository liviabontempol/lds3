package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    
    @GetMapping("/alunos")
    public List<Aluno> visualizarTodosOsAlunos(){
        return alunoRepository.findAll();
    }

    @PostMapping("/cadastroaluno")
    public Aluno criarAluno(@RequestBody Aluno aluno){
        return alunoRepository.save(aluno);
    }

    @GetMapping("/aluno/{id}")
    public Optional<Aluno> visualizarAluno(@PathVariable int id){
        return alunoRepository.findById(id);
    }

    @PutMapping("/atualizaraluno/{id}")
    public Aluno atualizarAluno(@PathVariable int id, @RequestBody Aluno aluno){
        return alunoRepository.save(aluno);
    }


}
