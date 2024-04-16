package com.br.eCormmerce.service.usuarioService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.Infra.Security.TokenService;
import com.br.eCormmerce.dto.usuarioDTO.AuthenticationDTO;
import com.br.eCormmerce.dto.usuarioDTO.LoginResponseDTO;
import com.br.eCormmerce.dto.usuarioDTO.RegisterDTO;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.EnderecoRespository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class AuthenticationService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;
  @Autowired 
  private UsuarioRepository usuarioRepository;
  @Autowired
  private EnderecoRespository enderecoRespository;

  public ResponseEntity<Object> UsuarioLogin(AuthenticationDTO usuario){
    var usuarioPassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.password());
    var auth = this.authenticationManager.authenticate(usuarioPassword);
    var token = tokenService.generateToken((Usuario)auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDTO(token));
  }


  public ResponseEntity<Object> UsuarioRegister(RegisterDTO usuario){
    if (usuarioRepository.findByEmail(usuario.email()) != null) {
      String emailEmUso = "Email já em uso";
      return ResponseEntity.badRequest().body(emailEmUso);
    }
    if (enderecoRespository.existsById(usuario.enderecoId())) {
      Optional<Endereco> enderecoOptional = enderecoRespository.findById(usuario.enderecoId());
      Endereco endereco = enderecoOptional.get();
      String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.password());
      Usuario novoUsuario = new Usuario(usuario.email(), encryptedPassword, usuario.nome(), usuario.cpf(), usuario.role(), usuario.saldo(), usuario.cep(), usuario.rua(), usuario.enderecoId());
      novoUsuario.getEnderecos().add(endereco);
      usuarioRepository.save(novoUsuario);
      return ResponseEntity.ok(novoUsuario);
    }
    String enderecoNaoEncontrado = "Endereço nao encontrado";
    return ResponseEntity.badRequest().body(enderecoNaoEncontrado);
  }
}
