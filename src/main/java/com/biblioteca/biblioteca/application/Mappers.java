package com.biblioteca.biblioteca.application;

import org.mapstruct.Mapper;
import com.biblioteca.biblioteca.domain.dto.UsuarioDTO;
import com.biblioteca.biblioteca.domain.entity.Usuario;

@Mapper(componentModel = "spring")
public interface Mappers {
    
    UsuarioDTO UsuariotoDto(Usuario usuario);
    Usuario UsuarioDTOtoEntity(UsuarioDTO usuarioDTO);



}
