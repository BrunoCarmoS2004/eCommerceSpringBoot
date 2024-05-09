package com.br.eCormmerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;


@MappedSuperclass
public abstract class Pessoa {
  private String nome;
  
  @NotBlank(message = "CPF é obrigatório")
  @Column(unique = true)
  private String cpf;
  private String cep;
  
  private String sobreNome;
  private String numeroTelefone;

  private String logradouro;
  private String complemento;
  private String bairro;
  private String localidade;
  private String uf;
  private String pais;

  private Double saldo;
  
  public Pessoa(String nome, 
                String sobreNome, 
                String numeroTelefone, 
                String cpf, 
                String cep, 
                String logradouro, 
                String bairro, 
                String localidade, 
                String uf, 
                String pais) {
    this.nome = nome;
    this.sobreNome = sobreNome;
    this.numeroTelefone = numeroTelefone;
    this.cpf = cpf;
    this.cep = cep;
    this.logradouro = logradouro;
    this.bairro = bairro;
    this.localidade = localidade;
    this.uf = uf;
    this.pais = pais;
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
  public String getLogradouro() {
    return logradouro;
  }
  public String getComplemento() {
    return complemento;
  }
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }
  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }
  public String getBairro() {
    return bairro;
  }
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  public String getLocalidade() {
    return localidade;
  }
  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }
  public String getUf() {
    return uf;
  }
  public void setUf(String uf) {
    this.uf = uf;
  }
  
  public String getSobreNome() {
    return sobreNome;
  }
  public void setSobreNome(String sobreNome) {
    this.sobreNome = sobreNome;
  }
  public String getPais() {
    return pais;
  }
  public void setPais(String pais) {
    this.pais = pais;
  }
  public String getNumeroTelefone() {
    return numeroTelefone;
  }
  public void setNumeroTelefone(String numeroTelefone) {
    this.numeroTelefone = numeroTelefone;
  }
}
