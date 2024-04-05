package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<Vendas> compras;
    
    @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<Produtos> carrinho;
    
    private Double total;

    
    public Cliente(String nome, String cpf, double saldo) {
        super(nome, cpf, saldo);
    }
    public Cliente(String nome, String cpf) {
        super(nome, cpf);
    }
    public Cliente(){
    }

    public Long getId() {
        return id;
    }
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Vendas> getCompras() {
        return compras;
    }
    public void setCompras(List<Vendas> compras) {
        this.compras = compras;
    }
    public List<Produtos> getCarrinho() {
        return carrinho;
    }
    public void setCarrinho(Produtos carrinho) {
        this.carrinho.add(carrinho);
    }
    public void setCarrinho(List<Produtos> carrinho) {
        this.carrinho = carrinho;
    }

    public void removerProdutoCarrinho(Produtos produto){
        this.carrinho.remove(produto);
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}
