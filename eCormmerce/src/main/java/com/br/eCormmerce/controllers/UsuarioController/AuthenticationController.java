package com.br.eCormmerce.controllers.UsuarioController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.Infra.Security.TokenService;
import com.br.eCormmerce.dto.usuarioDTO.AuthenticationDTO;
import com.br.eCormmerce.dto.usuarioDTO.LoginResponseDTO;
import com.br.eCormmerce.dto.usuarioDTO.RegisterDTO;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;

  @Autowired UsuarioRepository usuarioRepository;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO usuario){
      var usuarioPassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.password());
      var auth = this.authenticationManager.authenticate(usuarioPassword);
      var token = tokenService.generateToken((Usuario)auth.getPrincipal());
      return ResponseEntity.ok(new LoginResponseDTO(token));
  }
  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO usuario){
    if (usuarioRepository.findByEmail(usuario.email()) != null) {
      return ResponseEntity.badRequest().build();
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.password());
    Usuario novoUsuario = new Usuario(usuario.email(), encryptedPassword, usuario.nome(), usuario.cpf(), usuario.role());
    this.usuarioRepository.save(novoUsuario);
    return ResponseEntity.ok(novoUsuario);
  }
}
