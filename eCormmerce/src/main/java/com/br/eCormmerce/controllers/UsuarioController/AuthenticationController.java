package com.br.eCormmerce.controllers.UsuarioController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.dto.usuarioDTO.AuthenticationDTO;
import com.br.eCormmerce.dto.usuarioDTO.RegisterDTO;
import com.br.eCormmerce.service.usuarioService.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO usuario){
    System.out.println("Teste");
      return authenticationService.UsuarioLogin(usuario);
  }
  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO usuario){
    return authenticationService.UsuarioRegister(usuario);
  }
}
