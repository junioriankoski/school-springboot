package com.example.escola.professor;

import org.springframework.stereotype.Service;

import com.example.escola.exception.RecursosNaoEncontradosException;

import java.util.List;


@Service
public class ProfessorService {
    
    private ProfessorRepository  repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<ProfessorResponse> listarTodos() {
        return repository.findAll()
            .stream()
            .map(professor -> new ProfessorResponse(
                professor.getId(),
                professor.getNome(),
                professor.getEspecialidade()
            ))
            .toList();
    }

    public ProfessorResponse buscarPorId(Long id) {
        Professor professor = repository.findById(id)
            .orElseThrow(() -> new RecursosNaoEncontradosException("Professor não encontrado")); 

        return new ProfessorResponse(
            professor.getId(),
            professor.getNome(),
            professor.getEspecialidade()
        );
    }

    public ProfessorResponse salvar(ProfessorRequest professor) {
        Professor novoProfessor = new Professor(professor.getNome(),professor.getEspecialidade(),professor.getEmail());
        Professor salvo = repository.save(novoProfessor);
        return new ProfessorResponse(
            salvo.getId(),
            salvo.getNome(),
            salvo.getEspecialidade());
    }

    public ProfessorResponse atualizarProfessor(ProfessorRequest professorAtualizado, Long id) {
        Professor professor = repository.findById(id)
            .orElseThrow(() -> new RecursosNaoEncontradosException("Professor não encontrado"));
                professor.setNome(professorAtualizado.getNome());
                professor.setEspecialidade(professorAtualizado.getEspecialidade());
                Professor salvo = repository.save(professor);
        return new ProfessorResponse(
            salvo.getId(),
            salvo.getNome(),
            salvo.getEspecialidade()
            );
        }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}