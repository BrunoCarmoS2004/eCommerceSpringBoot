package com.br.eCormmerce.models.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Pessoa implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String email;
  private String password;
  private UserRole role;
  private Long enderecoId;
    
  @ManyToMany
  private List<Endereco> enderecos = new ArrayList<>();

  public Usuario(String email, String password, String nome, String cpf, UserRole role, double saldo, String cep, String rua, Long enderecoId){
    super(nome, cpf, saldo,rua,cep);
    this.email = email;
    this.password = password;
    this.role = role;
    this.enderecoId = enderecoId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CLIENTE"),new SimpleGrantedAuthority("ROLE_VENDEDOR"));
    }
    else if (this.role == UserRole.VENDEDOR){
      return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
    }else{
      return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
