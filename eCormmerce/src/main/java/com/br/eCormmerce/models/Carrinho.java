package com.br.eCormmerce.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrinho_id;

    private Double total = 0.0;

    @OneToOne
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Produtos> produto = new ArrayList<>();

    public Carrinho(Cliente cliente, List<Produtos> produto) {
        this.cliente = cliente;
        this.produto = produto;
    }
    public Carrinho(List<Produtos> produto) {
        this.produto = produto;
    }
    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente.getNome();
    }
    public Long getClienteId() {
        return cliente.getId();
    }

}
