package com.br.eCormmerce.dto.usuarioDTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    // Construtor, getters e setters

    public LoginResponseDTO(String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }
}