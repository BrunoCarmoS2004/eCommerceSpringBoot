package com.br.eCormmerce.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vendedor extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
    private List<Produtos> produtos;
    @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
    private List<Vendas> vendas;

    private Long enderecoId;
    
    @ManyToMany
    private List<Endereco> enderecos = new ArrayList<>();

    public Vendedor(String nome, String cpf, double saldo) {
        super(nome, cpf, saldo);
    }

    public Vendedor(String nome, String cpf) {
        super(nome, cpf);
    }

    public Vendedor(String nome, String cpf, double saldo, String cep, String rua, Long enderecoId) {
        super(nome, cpf, saldo,rua,cep);
        this.enderecoId = enderecoId;
    }

}
