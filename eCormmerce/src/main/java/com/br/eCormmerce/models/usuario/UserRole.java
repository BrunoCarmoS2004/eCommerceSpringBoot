package com.br.eCormmerce.models.usuario;

public enum UserRole {
  ADMIN("admin"),
  CLIENTE("cliente"),
  VENDEDOR("vendedor");

  private String role;

  public String getRole() {
    return role;
  }

  UserRole(String role){
    this.role = role;
  }
}
