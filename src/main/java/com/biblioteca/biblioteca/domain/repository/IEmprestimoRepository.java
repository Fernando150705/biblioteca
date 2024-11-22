package com.biblioteca.biblioteca.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.biblioteca.domain.entity.Emprestimo;

public interface IEmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}