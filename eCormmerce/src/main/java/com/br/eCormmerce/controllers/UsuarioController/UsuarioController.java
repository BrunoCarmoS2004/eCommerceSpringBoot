package com.br.eCormmerce.controllers.UsuarioController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.dto.usuarioDTO.UsuarioCpfDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioEmailDTO;
import com.br.eCormmerce.service.usuarioService.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @PutMapping("/atualizar")
  public ResponseEntity<Object> atualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
    return usuarioService.atualizarUsuario(usuarioDTO);
  }
  @DeleteMapping("/deletar")
  public ResponseEntity<Object> deletarUsuario(){
    return usuarioService.deletarUsuario();
  }
  @GetMapping("/verificar/email")
  public ResponseEntity<Object> verificarEmailEmUso(@RequestBody UsuarioEmailDTO verificar) {
     return usuarioService.verificarEmailEmUso(verificar);
  }
  @GetMapping("/verificar/cpf")
  public ResponseEntity<Object> verificarCPFEmUso(@RequestBody UsuarioCpfDTO verificar) {
      return usuarioService.verificarCPFEmUso(verificar);
  }
  
}
