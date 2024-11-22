package com.biblioteca.biblioteca.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.biblioteca.domain.entity.Livro;

public interface ILivroRepository extends JpaRepository<Livro, Long>{
    Optional<Livro> findByAnoPublicacao(int anoPublicacao);

    Optional<Livro> findByTitulo(String titulo);

    List<Livro> findByDisponibilidadeTrue();

    List<Livro> findByDisponibilidadeFalse();
}