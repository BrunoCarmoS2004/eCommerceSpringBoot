package com.br.eCormmerce.models;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Pessoa {
  @NotBlank(message = "Um nome é obrigatório")
  private String nome;

  @NotBlank(message = "CPF é obrigatório")
  @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter apenas números e ter 11 dígitos")
  @Column(unique = true)
  private String cpf;
  
  private Double saldo;
  
  public Pessoa(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }
  public Pessoa(String nome, String cpf, double saldo) {
    this.nome = nome;
    this.cpf = cpf;
    this.saldo = saldo;
  }
  public Pessoa() {
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public Double getSaldo() {
    return saldo;
  }
  public void setSaldo(Double preco) {
    this.saldo = preco;
  }
}
