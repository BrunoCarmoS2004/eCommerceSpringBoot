package com.br.eCormmerce.service.usuarioService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.usuarioDTO.UsuarioCpfDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioEmailDTO;
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

  public ResponseEntity<Object> verificarEmailEmUso(UsuarioEmailDTO verificar){
    System.out.println(usuarioRepository.findByEmail(verificar.email()));
    if (usuarioRepository.findByEmail(verificar.email()) != null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<Object> verificarCPFEmUso(UsuarioCpfDTO verificar){
    if (usuarioRepository.existsByCpf(verificar.cpf())) {
      String cpfEmUso = "CPF já em uso";
      return ResponseEntity.badRequest().body(cpfEmUso);
    }
    return ResponseEntity.ok().build();
  }

  public List<Usuario> listarUsuarioCliente(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosCliente = new ArrayList<>();
    for (Usuario usuario : usuariosList) {
      if (usuario.getRole() == UserRole.CLIENTE) {
        usuariosCliente.add(usuario);
      }
    }
    return usuariosCliente;
  }

  public List<Usuario> listarUsuarioVendedor(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosVendedor = new ArrayList<>();
    for (Usuario usuario : usuariosList) {
      if (usuario.getRole() == UserRole.VENDEDOR) {
        usuariosVendedor.add(usuario);
      }
    }
    return usuariosVendedor;
  }

  public List<Usuario> listarUsuarioAdmin(){
    List<Usuario> usuariosList = usuarioRepository.findAll();
    List<Usuario> usuariosAdmin = new ArrayList<>();
    for (Usuario usuario : usuariosList) {
      if (usuario.getRole() == UserRole.ADMIN) {
        usuariosAdmin.add(usuario);
      }
    }
    return usuariosAdmin;
  }

  public ResponseEntity<Object> atualizarUsuario(UsuarioDTO usuarioDTO){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    usuario.setCep(usuarioDTO.cep());
    usuario.setNome(usuarioDTO.nome());
    usuario.setLogradouro(usuarioDTO.rua());
    usuario.setId(usuario.getId());
    usuarioRepository.save(usuario);
    return ResponseEntity.ok(usuario);
  }

  public ResponseEntity<Object> deletarUsuario(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    usuarioRepository.deleteById(usuario.getId());
    String usuarioExcluido = "Usuario Excluido";
    return ResponseEntity.ok(usuarioExcluido);
  }
  public ResponseEntity<Object> adicionarSaldo(UsuarioSaldoDTO usuarioDTO){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
      Usuario usuario = (Usuario) usuarioDetails;
      usuario.setSaldo(usuario.getSaldo() + usuarioDTO.saldo());
      usuario.setId(usuario.getId());
      usuarioRepository.save(usuario);
      String usuarioSaldoAtualizado = "Foi adicionado saldo com sucesso\n"
      +"Novo saldo: "+usuario.getSaldo();
      return ResponseEntity.ok(usuarioSaldoAtualizado);
  }
}
