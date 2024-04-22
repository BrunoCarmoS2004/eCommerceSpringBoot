package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String produto_titulo;
    @NotBlank
    private double produto_preco;
    @NotBlank
    private int produto_quantidade;
    @NotBlank
    private String produto_descricao;
    @NotBlank
    private String produto_imagem;
    private int produto_qtd_vendas;
    @NotBlank
    private String vendedorId;
    @NotBlank
    private Long categoriaId;
    @NotBlank
    private String categoriaNome;

    @NotBlank
    @OneToMany(mappedBy = "produtosId", fetch = FetchType.LAZY)
    private List<Avaliacao>avaliacao;

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
