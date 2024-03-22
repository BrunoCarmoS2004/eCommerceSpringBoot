package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin>listarAdmin(){
        return adminService.listarAdmins();
    }

    public ResponseEntity<Object>criarAdmin(@Valid @RequestBody Admin admin){
        return adminService.criarAdmin(admin);
    }

    public ResponseEntity<Object>atualizarAdmin(@PathVariable Long id, @Valid Admin admin){
        return adminService.atualizarAdmin(id, admin);
    }

    public ResponseEntity<Object>deletarAdmin(@PathVariable Long id){
        return adminService.deletarAdmin(id);
    }
}
