package com.br.eCormmerce.models;

public abstract class Pessoa {
  private String nome;
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
