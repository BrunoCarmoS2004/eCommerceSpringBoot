package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendasRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class VendasServices {
  @Autowired
  private VendedorRepository vendedorRepository;
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired
  private VendasRepository vendasRepository;

  public List<Vendas>listarVendas(){
    return vendasRepository.findAll();
  }

  public ResponseEntity<Object>criarVendas(Vendas venda){
    if (clienteRepository.existsById(venda.getCliente_id())){
      if (vendedorRepository.existsById(venda.getVendedor_id())){
        if (produtosRepository.existsById(venda.getProduto_id())){
          Optional<Cliente> opCliente = clienteRepository.findById(venda.getCliente_id());
          Cliente cliente = opCliente.get();
          Optional<Vendedor> opVendedor = vendedorRepository.findById(venda.getVendedor_id());
          Vendedor vendedor = opVendedor.get();
          Optional<Produtos> opProduto = produtosRepository.findById(venda.getProduto_id());
          Produtos produto = opProduto.get();
          if (produto.getProduto_quantidade() - 1 >= 0){
            if (cliente.getSaldo() - produto.getProduto_preco() >= 0){
              produto.setProduto_quantidade(produto.getProduto_quantidade() - 1);
              cliente.setSaldo(cliente.getSaldo() - produto.getProduto_preco());
              vendedor.setSaldo(vendedor.getSaldo() + produto.getProduto_preco());
              produto.setProduto_qtd_vendas(produto.getProduto_qtd_vendas() + 1);
              //Fazer validação de quando o produto chegar a 0 ele ser excluido, fazer isso depois da entrega do trabalho
              return ResponseEntity.ok(vendasRepository.save(venda));
            }
            String produtosemquantidade = "Cliente sem o saldo suficiente";
            return ResponseEntity.badRequest().body(produtosemquantidade);
          }
          String produtosemquantidade = "Não a quantidade suficiente do produto";
          return ResponseEntity.badRequest().body(produtosemquantidade);
        }
        String produtoNaoCriado = "Não existe produto com esse id";
        return ResponseEntity.badRequest().body(produtoNaoCriado);
      }
      String produtoNaoCriado = "Não existe vendedor com esse id";
      return ResponseEntity.badRequest().body(produtoNaoCriado);
    }
    String produtoNaoCriado = "Não existe cliente com esse ID";
    return ResponseEntity.badRequest().body(produtoNaoCriado);
  }

  public ResponseEntity<Object>atualizarVendas(Long id, Vendas venda){
    //Fazer "Reibolso" para quando trocar o cliente ou vendedor devolver o dinheiro e fazer os tratamentos para o vendedor, fazer isso depois da entrega do trabalho
    if (vendasRepository.existsById(id)){
      if (clienteRepository.existsById(venda.getCliente_id())){
        if (vendedorRepository.existsById(venda.getVendedor_id())){
          if (produtosRepository.existsById(venda.getProduto_id())){
            
            venda.setVendas_id(id);
            return ResponseEntity.ok(vendasRepository.save(venda));
          }
        }
      } 
    } 
    String idVendaNaoExiste = "Não existe vendas com esse id";
    return ResponseEntity.badRequest().body(idVendaNaoExiste);  
  }

  public ResponseEntity<Object>deletarVendas(Long id){
    if (vendasRepository.existsById(id)){
      vendasRepository.deleteById(id);
      String vendaExcluida = "Venda excluida com sucesso!";
      return ResponseEntity.ok(vendaExcluida);
    }
    String vendaNaoExcluida = "Id de venda não encontrado!";
    return ResponseEntity.badRequest().body(vendaNaoExcluida);
  }
}
