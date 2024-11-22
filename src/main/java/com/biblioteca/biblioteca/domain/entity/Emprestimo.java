package com.biblioteca.biblioteca.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo", nullable = false)
    private long idEmprestimo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)  
    private Usuario usuario;  

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = true) 
    private Livro livro;  

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao_prevista", nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(name = "data_devolucao_real", nullable = true)
    private LocalDate dataDevolucaoReal;

    @Column(nullable = false)
    private String status;
    
    @Column(nullable = true)
    private Float multa;
}