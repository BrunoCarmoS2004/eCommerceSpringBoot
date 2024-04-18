package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.CategoriaRepository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<Categoria>listarCategoria(){
    return categoriaRepository.findAll();
  }

  public ResponseEntity<Object>criarCategoria(Categoria categoria){
    if (usuarioRepository.existsById(categoria.getAdminid())) {
      return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
    String categoriaNaoCriada = "Não existe Usuario admin com esse ID";
    return ResponseEntity.badRequest().body(categoriaNaoCriada);
  }

  public ResponseEntity<Object>atualizarCategoria(Long id, Categoria categoria){
    if(categoriaRepository.existsById(id)){
      categoria.setCategoria_id(id);
      return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
    String idCategoriaNaoEncontrado = "Não existe uma categoria com esse id";
    return ResponseEntity.badRequest().body(idCategoriaNaoEncontrado);
  }

  public ResponseEntity<Object>deletarCategoria(Long id){
    if (categoriaRepository.existsById(id)) {
      categoriaRepository.deleteById(id);
      String categoriaExcluida = "Categoria Excluida com sucesso!";
      return ResponseEntity.ok(categoriaExcluida);
    }
    String categoriaNaoExcluida = "Não existe uma categoria com esse id";
    return ResponseEntity.ok().body(categoriaNaoExcluida);
  }
}
