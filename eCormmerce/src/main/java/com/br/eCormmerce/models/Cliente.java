package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;
    private List<Produtos> produtos_id; 

    public Cliente(String nome, String cpf, double saldo) {
        super(nome, cpf, saldo);
    }

    public Cliente() {
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<Produtos> getProdutos_id() {
        return produtos_id;
    }

    public void setProdutos_id(List<Produtos> produtos_id) {
        this.produtos_id = produtos_id;
    }

}
