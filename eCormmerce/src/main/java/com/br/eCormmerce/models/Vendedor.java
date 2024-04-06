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
public class Vendedor extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
    private List<Produtos> produtos;
    @OneToMany(mappedBy = "vendedorId", fetch = FetchType.LAZY)
    private List<Vendas> vendas;

    public Vendedor(String nome, String cpf, double saldo) {
        super(nome, cpf, saldo);
    }

    public Vendedor(String nome, String cpf) {
        super(nome, cpf);
    }

}
