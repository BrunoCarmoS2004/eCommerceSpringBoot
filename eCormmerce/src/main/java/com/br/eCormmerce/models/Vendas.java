package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendas_id;
    @OneToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor_id;
    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto_id;
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente_id;

    public Vendas() {
    }
    public Vendas(Long vendas_id, Vendedor vendedor_id, Produtos produto_id, Cliente cliente_id) {
        this.vendas_id = vendas_id;
        this.vendedor_id = vendedor_id;
        this.produto_id = produto_id;
        this.cliente_id = cliente_id;
    }

    public Long getVendas_id() {
        return vendas_id;
    }
    public void setVendas_id(Long vendas_id) {
        this.vendas_id = vendas_id;
    }
    public Vendedor getVendedor_id() {
        return vendedor_id;
    }
    public void setVendedor_id(Vendedor vendedor_id) {
        this.vendedor_id = vendedor_id;
    }
    public Produtos getProduto_id() {
        return produto_id;
    }
    public void setProdutos_id(Produtos produto_id) {
        this.produto_id = produto_id;
    }
    public Cliente getCliente_id() {
        return cliente_id;
    }
    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
}
