package com.br.eCormmerce.service.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.Infra.Security.TokenService;
import com.br.eCormmerce.dto.usuarioDTO.AuthenticationDTO;
import com.br.eCormmerce.dto.usuarioDTO.LoginResponseDTO;
import com.br.eCormmerce.dto.usuarioDTO.RegisterDTO;
import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.CarrinhoRepository;
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
  private CarrinhoRepository carrinhoRepository;

  public ResponseEntity<Object> UsuarioLogin(AuthenticationDTO login){
    var usuarioPassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
    var auth = this.authenticationManager.authenticate(usuarioPassword);
    var token = tokenService.generateToken((Usuario)auth.getPrincipal());
    UserDetails usuario = usuarioRepository.findByEmail(login.email());
    return ResponseEntity.ok(new LoginResponseDTO
      (
      token, 
      usuario.getUsername(), 
      usuario.getAuthorities()
      ));
  }


  public ResponseEntity<Object> UsuarioRegister(RegisterDTO usuario){
    if (usuarioRepository.findByEmail(usuario.email()) != null) {
      String emailEmUso = "Email já em uso";
      return ResponseEntity.badRequest().body(emailEmUso);
    }
    if (usuarioRepository.existsByCpf(usuario.cpf())) {
      String cpfEmUso = "CPF já em uso";
      return ResponseEntity.badRequest().body(cpfEmUso);
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.password());
    Usuario novoUsuario = new Usuario
    (
    usuario.email(),
    encryptedPassword,
    usuario.nome(),
    usuario.sobreNome(),
    usuario.numeroTelefone(),
    usuario.cpf(),
    usuario.role(),
    usuario.cep(),
    usuario.logradouro(),
    usuario.complemento(),
    usuario.bairro(),
    usuario.localidade(),
    usuario.uf(),
    usuario.pais()
    );
    usuarioRepository.save(novoUsuario);
    Carrinho carrinho = new Carrinho(novoUsuario);
    carrinhoRepository.save(carrinho);
    return ResponseEntity.ok(novoUsuario);
  }
}
