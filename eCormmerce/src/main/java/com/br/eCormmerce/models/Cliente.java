package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
