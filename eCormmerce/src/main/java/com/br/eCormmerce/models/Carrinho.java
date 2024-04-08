package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrinho_id;

    @OneToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "carrinho", fetch = FetchType.LAZY)
    private List<Produtos> produtoId;

    public Carrinho(Cliente cliente, List<Produtos> produtoId) {
        this.cliente = cliente;
        this.produtoId = produtoId;
    }
    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getClienteNome() {
        return cliente.getNome();
    }
    public Long getClienteId() {
        return cliente.getId();
    }
   
    
}
