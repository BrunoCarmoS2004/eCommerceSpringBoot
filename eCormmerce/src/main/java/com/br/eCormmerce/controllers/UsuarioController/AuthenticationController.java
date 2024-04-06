package com.br.eCormmerce.controllers.UsuarioController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.dto.usuarioDTO.AuthenticationDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @PostMapping("login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO usuario){
      var usuarioPassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.password());
      var auth = 
  }
}
