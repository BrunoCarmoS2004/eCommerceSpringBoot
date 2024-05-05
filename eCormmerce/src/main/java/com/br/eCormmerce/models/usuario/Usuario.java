package com.br.eCormmerce.models.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.Pessoa;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
  @Column(unique = true)
  @NotBlank(message = "O campo email não pode estar em branco")
  @Pattern(regexp = ".*@.*\\.com", message = "O email deve conter '@' e .com")
  private String email;
  private String password;
  private UserRole role;
  private Long enderecoId;
    
  @ManyToMany
  private List<Endereco> enderecos = new ArrayList<>();
  //Carrinho de todos os usuarios
  @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Carrinho carrinho;
  //Compras do cliente
  @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Vendas> compras;
  //Avaliações do cliente
  @OneToMany(mappedBy = "usuarioId", fetch = FetchType.LAZY)
  private List<Avaliacao> avaliacoes;
  //Vendas do Usuario Vendedor
  @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
  private List<Vendas> vendas;
  //Produtos do Usuario Vendedor
  @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
  private List<Produtos> produtos;
  //Categorias do Usuario Admin
  @OneToMany(mappedBy = "adminid", fetch = FetchType.LAZY)
  private List<Categoria> categorias;





  public Usuario(String email, String password, String nome, String cpf, UserRole role, String cep, String rua, Long enderecoId){
    super(nome, cpf,rua,cep);
    this.email = email;
    this.password = password;
    this.role = role;
    this.enderecoId = enderecoId;
  }
  public Usuario(String nome, String cpf, UserRole role, String cep, String rua, Long enderecoId){
    super(nome, cpf,rua,cep);
    this.role = role;
    this.enderecoId = enderecoId;
  }
  public Usuario(String id, Double saldo){
    super(saldo);
    this.id = id;
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


  public UserRole getRole() {
    return role;
  }
 
  public Carrinho getCarrinho() {
    if (this.role == UserRole.ADMIN || this.role == UserRole.CLIENTE) {
      return carrinho;
    }else{
      return null;
    }
  }

  public List<Vendas> getCompras() {
    if (this.role == UserRole.ADMIN || this.role == UserRole.CLIENTE) {
      return compras;
    }else{
      return null;
    }
  }

  public List<Avaliacao> getAvaliacoes() {
    if (this.role == UserRole.ADMIN || this.role == UserRole.CLIENTE) {
      return avaliacoes;
    }else{
      return null;
    }
  }
 
  public List<Vendas> getVendas() {
    if (this.role == UserRole.ADMIN || this.role == UserRole.VENDEDOR) {
      return vendas;
    }else{
      return null;
    }
  }
 
  public List<Produtos> getProdutos() {
    if (this.role == UserRole.ADMIN || this.role == UserRole.VENDEDOR) {
      return produtos;
    }else{
      return null;
    }
  }
  
  public List<Categoria> getCategorias() {
    if (this.role == UserRole.CLIENTE || this.role == UserRole.VENDEDOR) {
      return categorias;
    }else{
      return null;
    }
  }
 
}
