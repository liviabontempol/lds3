package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.model.Empresa;
import com.example.demo.model.Vantagem;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VantagemRepository;

@Service
public class EmpresaService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VantagemRepository vantagemRepository;

    public Vantagem criarVantagem(String emailEmpresa, int valor, String descricao, String foto) {
        User userEmpresa = userRepository.findByEmail(emailEmpresa);
        if (userEmpresa == null || !(userEmpresa instanceof Empresa)) {
            throw new IllegalArgumentException("Empresa não encontrada com o email: " + emailEmpresa);
        }
        Empresa empresa = (Empresa) userEmpresa;

        Vantagem vantagem = new Vantagem(valor, descricao, foto);

        empresa.addVantagens(vantagem);

        vantagemRepository.save(vantagem);
        userRepository.save(empresa);

        return vantagem;
    }
    
    public List<Vantagem> listarVantagensEmpresa(String emailEmpresa) {
        User userEmpresa = userRepository.findByEmail(emailEmpresa);
        if (userEmpresa == null || !(userEmpresa instanceof Empresa)) {
            throw new IllegalArgumentException("Empresa não encontrada com o email: " + emailEmpresa);
        }
        Empresa empresa = (Empresa) userEmpresa;
        return empresa.getVantagens();
    }

    
}