package com.br.eCormmerce.service.usuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.usuarioDTO.UsuarioDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioSaldoDTO;
import com.br.eCormmerce.models.usuario.UserRole;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

public ResponseEntity<Object> verificarEmailEmUso(String email){
    boolean emailEmUso = usuarioRepository.findByEmail(email) != null;
    Map<String, Boolean> response = new HashMap<>();
    response.put("emailEmUso", emailEmUso);
    return ResponseEntity.ok(response);
}

  public ResponseEntity<Object> verificarCPFEmUso(String cpf){
    boolean cpfEmUso = usuarioRepository.findByCpf(cpf) != null;
    Map<String, Boolean> response = new HashMap<>();
    response.put("cpfEmUso", cpfEmUso);
    return ResponseEntity.ok(response);
  }

  public List<Usuario> listarUsuarioCliente(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosCliente = new ArrayList<>();
    usuariosCliente = usuariosList.stream().filter(cliente -> cliente.getRole().equals(UserRole.CLIENTE)).collect(Collectors.toList());
    return usuariosCliente;
  }

  public List<Usuario> listarUsuarioVendedor(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosVendedor = new ArrayList<>();
    usuariosVendedor = usuariosList.stream().filter(vendedor -> vendedor.getRole().equals(UserRole.VENDEDOR)).collect(Collectors.toList());
    return usuariosVendedor;
  }

  public List<Usuario> listarUsuarioAdmin(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosAdmin = new ArrayList<>();
    usuariosAdmin = usuariosList.stream().filter(admin -> admin.getRole().equals(UserRole.ADMIN)).collect(Collectors.toList());
    return usuariosAdmin;
  }

  public ResponseEntity<Object> validarRoleUsuario(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    UserDetails userDetails = usuarioRepository.findByEmail(principal.getUsername());
    Usuario usuario = (Usuario) userDetails;
    Map<String, String> response = new HashMap<>();
    if (usuario.getRole() == UserRole.ADMIN) {
      response.put("usuario","admin");
      return ResponseEntity.ok(response);
    }
    if (usuario.getRole() == UserRole.CLIENTE){
      response.put("usuario", "cliente");
      return ResponseEntity.ok(response);
    }
    response.put("usuario", "vendedor");
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<Object> atualizarUsuario(UsuarioDTO usuarioDTO){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    UserDetails userDetails = usuarioRepository.findByEmail(principal.getUsername());
    Usuario usuario = (Usuario) userDetails;
    usuario.setCep(usuarioDTO.cep());
    usuario.setNome(usuarioDTO.nome());
    usuario.setLogradouro(usuarioDTO.rua());
    usuario.setId(usuario.getId());
    usuarioRepository.save(usuario);
    return ResponseEntity.ok(usuario);
  }

  public ResponseEntity<Object> deletarUsuario(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    UserDetails userDetails = usuarioRepository.findByEmail(principal.getUsername());
    Usuario usuario = (Usuario) userDetails;
    usuarioRepository.deleteById(usuario.getId());
    String usuarioExcluido = "Usuario Excluido";
    return ResponseEntity.ok(usuarioExcluido);
  }
  public ResponseEntity<Object> adicionarSaldo(UsuarioSaldoDTO usuarioDTO){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails principal = (UserDetails) authentication.getPrincipal();
      UserDetails userDetails = usuarioRepository.findByEmail(principal.getUsername());
      Usuario usuario = (Usuario) userDetails;
      usuario.setSaldo(usuario.getSaldo() + usuarioDTO.saldo());
      usuario.setId(usuario.getId());
      usuarioRepository.save(usuario);
      String usuarioSaldoAtualizado = "Foi adicionado saldo com sucesso\n"
      +"Novo saldo: "+usuario.getSaldo();
      return ResponseEntity.ok(usuarioSaldoAtualizado);
  }
}
