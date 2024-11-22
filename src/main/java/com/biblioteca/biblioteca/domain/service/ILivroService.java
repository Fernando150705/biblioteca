package com.biblioteca.biblioteca.domain.service;

import java.util.List;

import com.biblioteca.biblioteca.domain.dto.LivroDTO;

public interface ILivroService {
    
    LivroDTO buscarPorId(Long id);

    LivroDTO buscarPorTitulo(String titulo);

    List<LivroDTO> listarLivrosDisponiveis();

    List<LivroDTO> listarLivrosEmprestados();

    List<LivroDTO> listarTodosLivros();

    LivroDTO cadastrarLivro(LivroDTO livroDTO);

    LivroDTO atualizarLivro(Long id, LivroDTO livroAtualizado);

    void removerLivro(Long id);
    
}