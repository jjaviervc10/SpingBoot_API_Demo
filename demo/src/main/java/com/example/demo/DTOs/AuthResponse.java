package com.example.demo.DTOs;

public class AuthResponse {
    private String token;
    private UsuarioDTO usuario;

    // Constructor
    public AuthResponse(String token, UsuarioDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
