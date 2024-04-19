package com.br.eCormmerce.repositorys.usuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.eCormmerce.models.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
  UserDetails findByEmail(String email);
  Boolean existsByCpf(String cpf);
}
