package com.example.escola.aluno;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public boolean existeAluno(Long id) {
      return  repository.existsById(id);
    }

    public List<AlunoResponse> listarTodos() {
        return repository.findAll()
            .stream()
            .map(aluno -> new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCurso()
            ))
            .toList();
    }

    public Optional<AlunoResponse> buscarPorId(Long id) {
        return repository.findById(id)
            .map(aluno -> new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCurso()
            ));
    }

    public Optional<AlunoResponse> atualizarAluno(AlunoRequest alunoAtualizado, Long id) {
        return repository.findById(id)
            .map(aluno -> {
                aluno.setNome(alunoAtualizado.getNome());
                aluno.setIdade(alunoAtualizado.getIdade());
                aluno.setCurso(alunoAtualizado.getCurso());
                Aluno salvo = repository.save(aluno);
                return new AlunoResponse(
                    salvo.getId(),
                    salvo.getNome(),
                    salvo.getCurso());
            });
    }
       
    public AlunoResponse salvar(AlunoRequest aluno) {
        Aluno novoAluno = new Aluno(aluno.getNome(), aluno.getIdade(), aluno.getCurso());
        Aluno salvo = repository.save(novoAluno);
        return new AlunoResponse(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCurso()
        );
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }
    public Aluno procurarAluno(Long id) {
        return repository.findById(id).orElse(null);
    }
}