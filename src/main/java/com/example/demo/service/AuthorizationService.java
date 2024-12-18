package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;


/**
 * Serviço para autenticação de usuários.
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Carrega um usuário pelo nome de usuário (login).
     * 
     * @param username O nome de usuário (login) do usuário a ser carregado.
     * @return Os detalhes do usuário encontrado.
     * @throws UsernameNotFoundException Se o usuário com o nome de usuário
     *                                   fornecido não puder ser encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}