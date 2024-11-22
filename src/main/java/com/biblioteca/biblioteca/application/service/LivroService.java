package com.biblioteca.biblioteca.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.application.Mappers;
import com.biblioteca.biblioteca.domain.dto.LivroDTO;
import com.biblioteca.biblioteca.domain.entity.Livro;
import com.biblioteca.biblioteca.domain.repository.ILivroRepository;
import com.biblioteca.biblioteca.domain.service.ILivroService;
import com.biblioteca.biblioteca.shared.CustomException;

@Service
public class LivroService implements ILivroService {
    
    @Autowired
    private ILivroRepository livroRepository;

    @Autowired
    private Mappers livroMapper;

    @Override
    public LivroDTO buscarPorId(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new CustomException("Livro não encontrado com o ID: " + id);
        }

        return livroMapper.LivrotoDto(livro.get());
    }

    @Override
    public List<LivroDTO> listarTodosLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(livroMapper::LivrotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LivroDTO> listarLivrosDisponiveis() {
        // Utiliza o repositório para pegar livros com disponibilidade true
        List<Livro> livrosDisponiveis = livroRepository.findByDisponibilidadeTrue();
        
        // Converte os livros disponíveis para DTO
        return livrosDisponiveis.stream()
                .map(livroMapper::LivrotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LivroDTO> listarLivrosEmprestados() {

        List<Livro> livrosDisponiveis = livroRepository.findByDisponibilidadeFalse();
        
        return livrosDisponiveis.stream()
                .map(livroMapper::LivrotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public LivroDTO buscarPorTitulo(String titulo) {
        Optional<Livro> livro = livroRepository.findByTitulo(titulo);

        if (livro.isEmpty()) {
            throw new CustomException("Livro nao encontrado com o titulo: " + titulo);
        }

        return livroMapper.LivrotoDto(livro.get());
    }

    @Override
    public LivroDTO cadastrarLivro(LivroDTO livroDTO) {
   
        Livro livro = livroMapper.LivroDTOtoEntity(livroDTO);
        livro = livroRepository.save(livro);

        return livroMapper.LivrotoDto(livro);
    }


    @Override
    public LivroDTO atualizarLivro(Long id, LivroDTO livroAtualizado) {
        Optional<Livro> livroExistente = livroRepository.findById(id);

        if (livroExistente.isEmpty()) {
            throw new CustomException("Livro não encontrado com o ID: " + id);
        }

        Livro livro = livroExistente.get();

        livro = livroRepository.save(livro);

        return livroMapper.LivrotoDto(livro);
    }
    
    @Override
    public void removerLivro(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new CustomException("Livro não encontrado com o ID: " + id);
        }

        livroRepository.deleteById(id);
    }

    

}


