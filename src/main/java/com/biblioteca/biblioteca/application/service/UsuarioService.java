package com.biblioteca.biblioteca.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.application.Mappers;
import com.biblioteca.biblioteca.domain.dto.UsuarioDTO;
import com.biblioteca.biblioteca.domain.entity.Usuario;
import com.biblioteca.biblioteca.domain.repository.IUsuarioRepository;
import com.biblioteca.biblioteca.domain.service.IUsuarioService;
import com.biblioteca.biblioteca.shared.CustomException;

import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private Mappers usuarioMapper;

    // Cadastrar um novo usuário
    @Override
    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
        // Mapeia o DTO para a entidade e salva no banco de dados
        Usuario usuario = usuarioMapper.UsuarioDTOtoEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        // Retorna o DTO mapeado a partir da entidade salva
        return usuarioMapper.UsuariotoDto(usuario);
    }

    // Atualizar um usuário existente
    @Override
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }

        // Atualiza os dados do usuário existente com as informações do DTO
        Usuario usuario = usuarioExistente.get();
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
      // Salva o usuário atualizado no repositório
        usuario = usuarioRepository.save(usuario);

        // Retorna o DTO atualizado mapeado a partir da entidade salva
        return usuarioMapper.UsuariotoDto(usuario);
    }

    // Remover um usuário pelo ID
    @Override
    public void removerUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }

    // Listar todos os usuários
    @Override
    public List<UsuarioDTO> listarTodosUsuarios() {
        // Obtém a lista de entidades de usuários e a converte para uma lista de DTOs
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::UsuariotoDto) // Mapeia cada entidade para seu DTO correspondente
                .collect(Collectors.toList()); // Coleta os resultados em uma lista de DTOs
    }

    // Buscar usuário por ID
    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }

        // Retorna o DTO mapeado a partir da entidade encontrada
        return usuarioMapper.UsuariotoDto(usuario.get());
    }

    // Buscar usuário por email
    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new CustomException("Usuário não encontrado com o email: " + email);
        }

        // Retorna o DTO mapeado a partir da entidade encontrada
        return usuarioMapper.UsuariotoDto(usuario.get());
    }

}

