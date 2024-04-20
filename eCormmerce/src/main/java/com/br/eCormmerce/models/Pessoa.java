package com.br.eCormmerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Pessoa {
  private String nome;
  @NotBlank(message = "CPF é obrigatório")
  @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter apenas números e ter 11 dígitos")
  @Column(unique = true)
  private String cpf;
  
  private String cep;
  private String rua;
  
  private Double saldo;
  
  public Pessoa(String nome, String cpf, Double saldo, String cep, String rua) {
    this.nome = nome;
    this.cpf = cpf;
    this.cep = cep;
    this.rua = rua;
    this.saldo = saldo;
  }
  public Pessoa(String nome, String cpf, String cep, String rua) {
    this.nome = nome;
    this.cpf = cpf;
    this.cep = cep;
    this.rua = rua;
  }
  public Pessoa(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }
  public Pessoa(String nome, String cpf, double saldo) {
    this.nome = nome;
    this.cpf = cpf;
    this.saldo = saldo;
  }
  public Pessoa(double saldo) {
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
  public String getCep() {
    return cep;
  }
  public void setCep(String cep) {
    this.cep = cep;
  }
  public String getRua() {
    return rua;
  }
  public void setRua(String rua) {
    this.rua = rua;
  }
}
