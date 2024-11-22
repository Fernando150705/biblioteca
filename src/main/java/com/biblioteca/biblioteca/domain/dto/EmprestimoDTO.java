package com.biblioteca.biblioteca.domain.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmprestimoDTO {
    private long idEmprestimo;
    private LivroDTO livroDTO;
    private UsuarioDTO usuarioDTO;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private String status;
    private float multa;
}