package com.example.demo.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Configurações de segurança para a aplicação.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    /**
     * Configura o filtro de segurança para definir as políticas de segurança da
     * aplicação.
     * 
     * @param httpSecurity O objeto HttpSecurity para configurar as políticas de
     *                     segurança.
     * @return Um filtro de segurança configurado com as políticas especificadas.
     * @throws Exception Se ocorrer um erro ao configurar as políticas de segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception{
        return httpsecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /**
     * Retorna o bean do gerenciador de autenticação.
     * 
     * @return O gerenciador de autenticação configurado.
     * @throws Exception Se ocorrer um erro ao obter o gerenciador de autenticação.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Retorna o bean do codificador de senha.
     * 
     * @return O codificador de senha configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}