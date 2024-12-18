package com.example.demo.model;
/**
 * Enumeração que define os tipos de usuário.
 */
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    /**
     * Construtor public para inicializar o tipo de usuário com a role
     * correspondente.
     * 
     * @param role A role associada ao tipo de usuário.
     */
    private UserRole(String role) {
        this.role = role;
    }

    /**
     * Obtém a role associada ao tipo de usuário.
     * @return A role do tipo de usuário.
     */
    public String getRole() {
        return role;
    }
}