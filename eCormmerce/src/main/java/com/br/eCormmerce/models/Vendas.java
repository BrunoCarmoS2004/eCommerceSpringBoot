package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendas_id;
    
    private Long vendedorId;
    
    private Long produtosId;
    
    private Long clienteId;

    public Vendas() {
    }
    public Vendas(Long vendedorId, Long produtosId, Long clienteId) {
        this.vendedorId = vendedorId;
        this.produtosId = produtosId;
        this.clienteId = clienteId;
    }

    public Long getVendas_id() {
        return vendas_id;
    }
    public void setVendas_id(Long vendas_id) {
        this.vendas_id = vendas_id;
    }
    public Long getVendedor_id() {
        return vendedorId;
    }
    public void setVendedor_id(Long vendedorId) {
        this.vendedorId = vendedorId;
    }
    public Long getProduto_id() {
        return produtosId;
    }
    public void setProdutos_id(Long produtosId) {
        this.produtosId = produtosId;
    }
    public Long getCliente_id() {
        return clienteId;
    }
    public void setCliente_id(Long clienteId) {
        this.clienteId = clienteId;
    }
    public Long getVendedorId() {
        return vendedorId;
    }
    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }
    public Long getProdutosId() {
        return produtosId;
    }
    public void setProdutosId(Long produtosId) {
        this.produtosId = produtosId;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
