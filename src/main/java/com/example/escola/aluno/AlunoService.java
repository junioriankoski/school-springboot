package com.example.escola.aluno;

import org.springframework.stereotype.Service;

import com.example.escola.exception.RecursosNaoEncontradosException;
import com.example.escola.professor.ProfessorRepository;
import com.example.escola.professor.Professor;

import java.util.List;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;

    public AlunoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public boolean existeAluno(Long id) {
      return  alunoRepository.existsById(id);
    }

    public List<AlunoResponse> listarTodos() {
        return alunoRepository.findAll()
            .stream()
            .map(aluno -> new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCurso(),
                aluno.getProfessor() != null ? aluno.getProfessor().getNome() : null
            ))
            .toList();
    }

    public AlunoResponse buscarPorId(Long id) {
       Aluno aluno = alunoRepository.findById(id)
            .orElseThrow(() -> new RecursosNaoEncontradosException("Aluno não encontrado"));

        return new AlunoResponse(
            aluno.getId(),
            aluno.getNome(),
            aluno.getCurso(),
            aluno.getProfessor() != null ? aluno.getProfessor().getNome() : null
        );
    }

    public AlunoResponse atualizarAluno(AlunoRequest alunoAtualizado, Long id) {
        Aluno aluno = alunoRepository.findById(id)
           .orElseThrow(() -> new RecursosNaoEncontradosException("Aluno não encontrado"));
        Professor professor = professorRepository.findById(alunoAtualizado.getProfessorId())
            .orElseThrow(() -> new RecursosNaoEncontradosException("Professor não encontrado"));
        aluno.setProfessor(professor);
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setIdade(alunoAtualizado.getIdade());
        aluno.setCurso(alunoAtualizado.getCurso());
        Aluno salvo = alunoRepository.save(aluno);
        return new AlunoResponse(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCurso(),
            salvo.getProfessor() != null ? salvo.getProfessor().getNome() : null
        );
    }
    
       
    public AlunoResponse salvar(AlunoRequest aluno) {
        Professor professor = professorRepository.findById(aluno.getProfessorId())
            .orElseThrow(() -> new RecursosNaoEncontradosException("Professor não encontrado"));
        Aluno novoAluno = new Aluno(aluno.getNome(), aluno.getIdade(), aluno.getCurso());
        novoAluno.setProfessor(professor);
        Aluno salvo = alunoRepository.save(novoAluno);
        return new AlunoResponse(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCurso(),
            salvo.getProfessor() != null ? salvo.getProfessor().getNome() : null
        );
    }

    public boolean deletar(Long id) {
        if (!alunoRepository.existsById(id)){
            return false;
        }
        alunoRepository.deleteById(id);
        return true;
    }
    public Aluno procurarAluno(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }
}