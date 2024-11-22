package com.biblioteca.biblioteca.domain.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private LocalDate dataCadastro;
    private int quantidadeLivrosEmprestados;
}