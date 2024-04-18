package com.br.eCormmerce.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.VendasDTO;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendasRepository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class VendasServices {
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired
  private VendasRepository vendasRepository;

  public List<Vendas>listarVendas(){
    return vendasRepository.findAll();
  }

  public ResponseEntity<Object>atualizarVendas(Long id, VendasDTO vendaDto){
    if (vendasRepository.existsById(id)){
      if (usuarioRepository.existsById(vendaDto.clienteId())){
        if (usuarioRepository.existsById(vendaDto.vendedorId())){
          if (produtosRepository.existsById(vendaDto.produtosId())){
            Optional<Vendas> vendaOptional = vendasRepository.findById(id);
            Vendas venda = vendaOptional.get();
            venda.setClienteId(vendaDto.clienteId());
            venda.setProdutosId(vendaDto.produtosId());
            venda.setVendedorId(vendaDto.vendedorId());
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

  public ResponseEntity<Object> vendedorDestaque() {
    List<Usuario> allVendedores = usuarioRepository.findAll();
    // Encontra o vendedor com mais vendas usando stream
    Usuario vendedor = allVendedores.stream().max(Comparator.comparingInt(vendedorDestaque -> vendedorDestaque.getVendas().size())).orElse(null);
    if (vendedor == null) {
        String naoHaVendas = "Não há nenhuma venda registrada!";
        return ResponseEntity.badRequest().body(naoHaVendas);
    }
    return ResponseEntity.ok(vendedor);
  }
}
