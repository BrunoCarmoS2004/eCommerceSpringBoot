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
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produto_id;
    private String produto_titulo;
    private double produto_preco;
    private int produto_quantidade;
    private String produto_descricao;
    private String produto_imagem;
    private int produto_qtd_vendas;
    private String vendedorId;
    private Long categoriaId;
    private String categoriaNome;


    @OneToMany(mappedBy = "produtosId", fetch = FetchType.LAZY)
    private List<Avaliacao>avaliacao;
    //@ManyToOne
    //private Carrinho carrinho;
    

    public Produtos(String produto_titulo, double produto_preco, int produto_quantidade, String produto_descricao,
            String produto_imagem, String vendedorId, Long categoriaId) {
        this.produto_titulo = produto_titulo;
        this.produto_preco = produto_preco;
        this.produto_quantidade = produto_quantidade;
        this.produto_descricao = produto_descricao;
        this.produto_imagem = produto_imagem;
        this.vendedorId = vendedorId;
        this.categoriaId = categoriaId;
    }

}
