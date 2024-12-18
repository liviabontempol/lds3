package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Empresa;
import com.example.demo.model.User;
import com.example.demo.model.Vantagem;
import com.example.demo.model.dto.VantagemRequestDTO;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmpresaService empresaService;

    @PostMapping("/vantagens")
    public ResponseEntity<?> criarVantagem(@RequestParam String emailEmpresa, 
                                           @RequestBody VantagemRequestDTO request) {
        try {
            Vantagem vantagem = empresaService.criarVantagem(
                    emailEmpresa,
                    request.valor(),
                    request.descricao(),
                    request.foto()
            );
            return ResponseEntity.ok(vantagem); // Retorna a vantagem criada
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trata erro com mensagem
        }
    }

    @GetMapping("/{email}/vantagens")
    public ResponseEntity<List<Vantagem>> listarVantagens(@PathVariable String email) {
        try {
            User user = userRepository.findByEmail(email);

            if (user == null || !(user instanceof Empresa)) {
                throw new IllegalArgumentException("Empresa não encontrada com o email: " + email);
            }

            Empresa empresa = (Empresa) user;
            return ResponseEntity.ok(empresa.getVantagens());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
