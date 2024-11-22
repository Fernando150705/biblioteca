package com.biblioteca.biblioteca.domain.service;

import java.util.List;

import com.biblioteca.biblioteca.domain.dto.UsuarioDTO;

public interface IUsuarioService {
    
    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO buscarPorEmail(String email);

    List<UsuarioDTO> listarTodosUsuarios();
   
    UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado);

    void removerUsuario(Long id);
}
