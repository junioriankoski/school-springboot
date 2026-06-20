package com.example.escola.professor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProfessorController {
    
    private ProfessorService service;

    ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/professores") 
        public List<ProfessorResponse> getProfessores() {
            return service.listarTodos();
        }
    
    @GetMapping("/professores/{id}")
        public ResponseEntity<ProfessorResponse> getProfessores(@PathVariable Long id) {
            return ResponseEntity.ok(service.buscarPorId(id));
        }
    
    @PostMapping("/professores")
        public ProfessorResponse salvarProfessor(@RequestBody ProfessorRequest professor) {
            return service.salvar(professor);
        }

    @PutMapping("/professores/{id}")
        public ResponseEntity<ProfessorResponse> atualizarProfessor(@RequestBody ProfessorRequest professorAtualizado, @PathVariable Long id) {
            return ResponseEntity.ok(service.atualizarProfessor(professorAtualizado, id));
        }

    @DeleteMapping("/professores/{id}")
        public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
            if (service.deletar(id)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
}
