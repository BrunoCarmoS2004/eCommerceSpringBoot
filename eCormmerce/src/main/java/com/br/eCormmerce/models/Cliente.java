package com.br.eCormmerce.models;

import jakarta.persistence.Entity;

@Entity
public class Cliente {
   private Long cliente_id;
   private String cliente_cpf;
   private String cliente_nome;
   private float cliente_saldo;
public Cliente(Long cliente_id, String cliente_cpf, String cliente_nome, float cliente_saldo) {
    this.cliente_id = cliente_id;
    this.cliente_cpf = cliente_cpf;
    this.cliente_nome = cliente_nome;
    this.cliente_saldo = cliente_saldo;
}
public Cliente() {
}
public Long getCliente_id() {
    return cliente_id;
}
public void setCliente_id(Long cliente_id) {
    this.cliente_id = cliente_id;
}
public String getCliente_cpf() {
    return cliente_cpf;
}
public void setCliente_cpf(String cliente_cpf) {
    this.cliente_cpf = cliente_cpf;
}
public String getCliente_nome() {
    return cliente_nome;
}
public void setCliente_nome(String cliente_nome) {
    this.cliente_nome = cliente_nome;
}
public float getCliente_saldo() {
    return cliente_saldo;
}
public void setCliente_saldo(float cliente_saldo) {
    this.cliente_saldo = cliente_saldo;
} 
}
