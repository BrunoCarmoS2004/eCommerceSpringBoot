package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.CategoriaDTO;
import com.br.eCormmerce.models.Categoria;
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

  public ResponseEntity<Object>criarCategoria(CategoriaDTO categoriaDTO){
    if (usuarioRepository.existsById(categoriaDTO.adminid())) {
      Categoria categoria = new Categoria(categoriaDTO.categoria_nome(), categoriaDTO.adminid());
      return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
    String categoriaNaoCriada = "Não existe Usuario admin com esse ID";
    return ResponseEntity.badRequest().body(categoriaNaoCriada);
  }

  public ResponseEntity<Object>atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
    if(categoriaRepository.existsById(id)){
      Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
      Categoria categoria = categoriaOptional.get();
      categoria.setAdminid(categoriaDTO.adminid());
      categoria.setCategoria_nome(categoriaDTO.categoria_nome());
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
