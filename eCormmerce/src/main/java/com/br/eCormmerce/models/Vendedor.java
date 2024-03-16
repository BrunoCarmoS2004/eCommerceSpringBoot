package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome_vendedor;
    private float vendedor_saldo;

    public Vendedor() {
    }
    public Vendedor(Long id, String cpf, String nome_vendedor, float vendedor_saldo) {
        this.id = id;
        this.cpf = cpf;
        this.nome_vendedor = nome_vendedor;
        this.vendedor_saldo = vendedor_saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_vendedor() {
        return nome_vendedor;
    }

    public void setNome_vendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor;
    }

    public float getvendedor_Saldo() {
        return vendedor_saldo;
    }

    public void setvendedor_Saldo(float vendedor_saldo) {
        this.vendedor_saldo = vendedor_saldo;
    }

    
}
