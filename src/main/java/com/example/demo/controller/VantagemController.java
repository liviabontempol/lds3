package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vantagem;
import com.example.demo.repository.VantagemRepository;

@RestController
public class VantagemController {
        @Autowired
    private VantagemRepository vantagemRepository;
    
    @GetMapping("/vantagens")
    public List<Vantagem> visualizarTodosAsVantagems(){
        return vantagemRepository.findAll();
    }

    @PostMapping("/cadastrovantagem")
    public Vantagem criarVantagem(@RequestBody Vantagem vantagem){
        return vantagemRepository.save(vantagem);
    }

    @GetMapping("/vantagem/{id}")
    public Optional<Vantagem> visualizarvantagem(@PathVariable int id){
        return vantagemRepository.findById(id);
    }

    @PutMapping("/atualizarvantagem/{id}")
    public Vantagem atualizarvantagem(@PathVariable int id, @RequestBody Vantagem vantagem){
        return vantagemRepository.save(vantagem);
    }

   // @DeleteMapping("")

}
