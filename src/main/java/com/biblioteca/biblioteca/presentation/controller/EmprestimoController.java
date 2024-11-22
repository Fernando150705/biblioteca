package com.biblioteca.biblioteca.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.biblioteca.domain.dto.EmprestimoDTO;
import com.biblioteca.biblioteca.domain.service.IEmprestimoService;
import com.biblioteca.biblioteca.shared.CustomException;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/emprestimo")
@Tag(name = "Emprestimos", description = "APIs relacionadas aos empréstimos")
public class EmprestimoController {
    
    @Autowired
    private IEmprestimoService emprestimoService;

    @GetMapping("/{idEmprestimo}")
    public ResponseEntity<EmprestimoDTO> buscarPorId(@PathVariable Long idEmprestimo) {
        try {
            EmprestimoDTO emprestimo = emprestimoService.buscarPorId(idEmprestimo);
            return ResponseEntity.ok(emprestimo);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listarTodosLivros() {
        try {
            List<EmprestimoDTO> emprestimo = emprestimoService.listarTodosEmprestimos();
            return ResponseEntity.ok(emprestimo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> registrarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            EmprestimoDTO savedEmprestimo = emprestimoService.registrarEmprestimo(emprestimoDTO);
            return ResponseEntity.status(201).body(savedEmprestimo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}