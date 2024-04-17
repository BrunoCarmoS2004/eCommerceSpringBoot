package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.Infra.Security.TokenService;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioDTO;
import com.br.eCormmerce.dto.usuarioDTO.UsuarioSaldoDTO;
import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.service.AvaliacaoService;
import com.br.eCormmerce.service.CarrinhoService;
import com.br.eCormmerce.service.ClienteService;
import com.br.eCormmerce.service.usuarioService.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AvaliacaoService avaliacaoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarClientes(){
        /*SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/
        return usuarioService.listarUsuario();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object> criarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.criarUsuario(cliente);
    }
    @PutMapping("/atualizar")
    public ResponseEntity<Object> atualizarCliente(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.atualizarUsuario(usuarioDTO);
    }
    @DeleteMapping("/deletar")
    public ResponseEntity<Object> deletarCliente(){
        return usuarioService.deletarUsuario();
    }

    @PatchMapping("/adicionar/saldo/{id}")
    public ResponseEntity<Object> adicionarSaldo(@RequestBody @Valid UsuarioSaldoDTO usuarioDTO){
        return usuarioService.adicionarSaldo(usuarioDTO);
    }

    //AVALIAÇÃO
    @GetMapping("/avaliarproduto")
    public List<Avaliacao> listarAvaliacaos(){
        return avaliacaoService.listarAvaliacoes();
    }
    @PostMapping("/avaliarproduto/criar")
    public ResponseEntity<Object> criarProduto(@Valid @RequestBody Avaliacao avaliacao){
        return avaliacaoService.criarAvaliacao(avaliacao);
    }

    @PutMapping("/avaliarproduto/atualizar/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Avaliacao avaliacao){
        return avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }

    @DeleteMapping("/avaliarproduto/deletar/{id}")
    public ResponseEntity<Object> deletarAvaliacao(@PathVariable Long id){
        return avaliacaoService.deletarAvaliacao(id);
    }
    //CARRINHO
    @PatchMapping("/carrinho")
    public ResponseEntity<Object> listarCarrinho(){
        return carrinhoService.listarCarrinho();
    }
    @PatchMapping("/carrinho/comprar")
    public ResponseEntity<Object> comprarTodosItens(){
      return carrinhoService.comprarTodosItens();
    }
    @PatchMapping("/carrinho/comprar/{produto_id}")
    public ResponseEntity<Object> comprarUmItem(@PathVariable Long produto_id){
      return carrinhoService.comprarUmItem(produto_id);
    }
    @PatchMapping("/carrinho/remover/{produto_id}")
    public ResponseEntity<Object> removerProdutosCarrinho(@PathVariable Long produto_id){
      return carrinhoService.removerProdutosCarrinho(produto_id);
    }
}
