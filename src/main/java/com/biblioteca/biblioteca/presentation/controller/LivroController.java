package com.biblioteca.biblioteca.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.biblioteca.domain.dto.LivroDTO;
import com.biblioteca.biblioteca.domain.service.ILivroService;
import com.biblioteca.biblioteca.shared.CustomException;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/livro")
@Tag(name = "Livros", description = "APIs relacionadas a Livros")
public class LivroController {
    
    @Autowired
    private ILivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodosLivros() {
        try {
            List<LivroDTO> livros = livroService.listarTodosLivros();
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long idLivro) {
        try {
            LivroDTO livro = livroService.buscarPorId(idLivro);
            return ResponseEntity.ok(livro);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDTO> buscarPorTitulo(@PathVariable String titulo) {
        try {
            LivroDTO livro = livroService.buscarPorTitulo(titulo);
            return ResponseEntity.ok(livro);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/LivrosDisponiveis")
    public ResponseEntity<List<LivroDTO>> listarLivrosDisponiveis() {
        List<LivroDTO> livrosDisponiveis = livroService.listarLivrosDisponiveis();
        return ResponseEntity.ok(livrosDisponiveis);
    }

    @GetMapping("/LivrosEmprestados")
    public ResponseEntity<List<LivroDTO>> listarLivrosEmprestados() {
        List<LivroDTO> livrosDisponiveis = livroService.listarLivrosEmprestados();
        return ResponseEntity.ok(livrosDisponiveis);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> cadastrarLivro(@RequestBody LivroDTO livroDTO) {
        try {
            LivroDTO savedLivro = livroService.cadastrarLivro(livroDTO);
            return ResponseEntity.status(201).body(savedLivro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long idLivro, @RequestBody LivroDTO livroAtualizado) {
        try {
            LivroDTO updatedLivro = livroService.atualizarLivro(idLivro, livroAtualizado);
            return ResponseEntity.ok(updatedLivro);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{idLivro}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long idLivro) {
        try {
            livroService.removerLivro(idLivro);
            return ResponseEntity.noContent().build();
        } catch (CustomException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

   

}