package com.biblioteca.biblioteca.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.application.Mappers;
import com.biblioteca.biblioteca.domain.dto.EmprestimoDTO;
import com.biblioteca.biblioteca.domain.entity.Emprestimo;
import com.biblioteca.biblioteca.domain.entity.Livro;
import com.biblioteca.biblioteca.domain.entity.Usuario;
import com.biblioteca.biblioteca.domain.repository.IEmprestimoRepository;
import com.biblioteca.biblioteca.domain.repository.ILivroRepository;
import com.biblioteca.biblioteca.domain.repository.IUsuarioRepository;
import com.biblioteca.biblioteca.domain.service.IEmprestimoService;
import com.biblioteca.biblioteca.shared.CustomException;

@Service
public class EmprestimoService implements IEmprestimoService {

    @Autowired
    private IEmprestimoRepository emprestimoRepository;

    @Autowired
    private ILivroRepository livroRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private Mappers emprestimoMapper;

    @Override
    public EmprestimoDTO buscarPorId(Long idEmprestimo) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(idEmprestimo);

        if (emprestimo.isEmpty()) {
            throw new CustomException("Livro não encontrado com o ID: " + idEmprestimo);
        }

        return emprestimoMapper.EmprestimotoDto(emprestimo.get());
    }

    @Override
    public List<EmprestimoDTO> listarTodosEmprestimos() {
        // Utiliza o repositório para pegar livros com disponibilidade true
        List<Emprestimo> emprestimoDisponiveis = emprestimoRepository.findAll();

        // Converte os livros disponíveis para DTO
        return emprestimoDisponiveis.stream()
                .map(emprestimoMapper::EmprestimotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmprestimoDTO registrarEmprestimo(EmprestimoDTO emprestimoDTO) {

        Emprestimo emprestimo = emprestimoMapper.EmprestimoDTOtoEntity(emprestimoDTO);


        Livro livro = livroRepository.findById(emprestimo.getLivro().getIdLivro())
                .orElseThrow(() -> new CustomException("Livro não encontrado "));
        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getIdUsuario())
                .orElseThrow(() -> new CustomException("Usuário não encontrado "));

        if (livro.isDisponibilidade() == false) {
            return null;
        }

        emprestimo = emprestimoRepository.save(emprestimo);
        livroRepository.save(livro);
        usuarioRepository.save(usuario);

        return emprestimoMapper.EmprestimotoDto(emprestimo);
    }


}