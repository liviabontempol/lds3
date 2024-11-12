package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Empresa;
import com.example.demo.repository.EmpresaRepository;

@RestController
public class EmpresaController {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/empresas")
    public List<Empresa> visualizarTodasAsEmpresas(){
        return empresaRepository.findAll();
    }

    @GetMapping("/empresa/{id}")
    public Optional<Empresa> visualizarEmpresa(@PathVariable int id){
        return empresaRepository.findById(id);
    }

    @PostMapping("/cadastroempresa")
    public Empresa cadastrarEmpresa(@RequestBody Empresa empresa){
        return empresaRepository.save(empresa);
    }

    @PutMapping("/atualizarempresa/{id}")
    public Empresa atualizarEmpresa(@PathVariable int id, @RequestBody Empresa empresa){
        return empresaRepository.save(empresa);
    }
}
