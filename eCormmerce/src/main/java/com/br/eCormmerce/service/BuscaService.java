package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.repositorys.ProdutosRepository;

@Service
public class BuscaService {
   @Autowired private ProdutosRepository produtosRepository;
    public List<Produtos> buscarProdutos(String produto_titulo){
        return produtosRepository.findAllByproduto_titulo(produto_titulo);
    }
}
