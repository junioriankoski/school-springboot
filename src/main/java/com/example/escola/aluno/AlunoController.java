package com.example.escola.aluno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;


@RestController 
public class AlunoController {

    private AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping("/alunos")
    public List<AlunoResponse> getAlunos() {
        return service.listarTodos();
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<AlunoResponse> getAluno(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alunos/existe/{id}")
    public boolean existe(@PathVariable Long id) {
        return service.existeAluno(id);
    }
    
    @PostMapping("/alunos")
    public AlunoResponse criarAluno(@RequestBody AlunoRequest aluno) {
        return service.salvar(aluno);
    }

    @PutMapping("/alunos/{id}")
    public ResponseEntity<AlunoResponse> atualizarAluno(@RequestBody AlunoRequest alunoAtualizado, @PathVariable Long id) {
        return service.atualizarAluno(alunoAtualizado, id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/alunos/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}