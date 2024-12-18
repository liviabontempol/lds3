package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Infra.Security.TokenService;
import com.example.demo.model.Aluno;
import com.example.demo.model.Professor;
import com.example.demo.model.Empresa;
import com.example.demo.model.User;
import com.example.demo.model.dto.AuthenticationDTO;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.model.dto.RegisterAlunoDTO;
import com.example.demo.model.dto.RegisterEmpresaDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthorizationService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint para autenticar um usuário.
     * 
     * @param data Os dados de autenticação do usuário.
     * @return Um ResponseEntity contendo o token de autenticação se a autenticação
     *         for bem-sucedida.
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        User authenticatedUser = (User) auth.getPrincipal();

        System.out.println("Usuário autenticado: " + authenticatedUser.getEmail());

        String token = tokenService.generateToken(authenticatedUser);

        userRepository.save(authenticatedUser);

        // Retornar resposta com o token
       String userType;
    if (authenticatedUser instanceof Aluno) {
        userType = "Aluno";
    } else if (authenticatedUser instanceof Empresa) {
        userType = "Empresa";
    } else if (authenticatedUser instanceof Professor) {
        userType = "Professor";
    } else {
        userType = "Unknown";
    }

    if (authenticatedUser instanceof Empresa) {
        authenticatedUser = userRepository.findById(authenticatedUser.getId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    // Cria o DTO de resposta
    var loginResponse = new LoginResponseDTO(
        authenticatedUser.getId(),
        userType,
        authenticatedUser // Inclui o objeto completo do usuário
    );

    return ResponseEntity.ok(loginResponse);
    }

    /**
     * Endpoint para registrar um novo usuário.
     * 
     * @param data Os dados do novo usuário.
     * @return Um ResponseEntity indicando se o registro foi bem-sucedido.
     */
    @PostMapping("/registerAluno")
    public ResponseEntity register(@RequestBody RegisterAlunoDTO data) {

        System.out.println("Dados recebidos: " + data);
        if (this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        
        User newUser = new Aluno(data.nome(), data.email(), encryptedPassword, data.curso(), data.emailInstituicao(), data.endereco(), data.cpf(), data.rg());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

       /**
     * Endpoint para registrar um novo usuário.
     * 
     * @param data Os dados do novo usuário.
     * @return Um ResponseEntity indicando se o registro foi bem-sucedido.
     */
    @PostMapping("/registerEmpresa")
    public ResponseEntity register(@RequestBody RegisterEmpresaDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        
        User newUser = new Empresa(data.nome(), data.email(), encryptedPassword, data.cnpj(), data.endereco());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}