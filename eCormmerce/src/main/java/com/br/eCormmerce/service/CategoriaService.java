package com.br.eCormmerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.repositorys.AdminRepository;
import com.br.eCormmerce.repositorys.CategoriaRepository;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private AdminRepository adminRepository;

  public List<Categoria>listarCategoria(){
    return categoriaRepository.findAll();
  }

  public ResponseEntity<Object>criarCategoria(Categoria categoria){
    if (adminRepository.existsById(categoria.getAdminid())) {
      return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
    String categoriaNaoCriada = "Não existe admin com esse ID";
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

  //CATEGORIA POR ADMIN
  public List<Categoria>listarCategoriaPorAdmin(Long id){
    /*Optional<Admin> adminOptional = adminRepository.findById(id);
  //if (adminOptional.isPresent()) {
    Admin admin = adminOptional.get();
    List<Categoria> categorias = categoriaRepository.findByAdminid(admin);
    return categorias;
  //} else {
    
  //}
    
   */
    List<Categoria> categorias = categoriaRepository.findByAdminid(id);
    return categorias;
  }
}
