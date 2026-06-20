package com.example.escola.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class LivroController {
    
    private LivroService service;

    public LivroController(LivroService service){
        this.service = service;
    }

    @GetMapping("/livros")
    public List<LivroResponse> getLivros() {
        return service.listarTodos();
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<LivroResponse> getLivros(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/livros")
    public LivroResponse adicionarLivro(@RequestBody LivroRequest livro) {
        return service.salvar(livro);
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@RequestBody LivroRequest livroAtualizado, @PathVariable Long id) {
        return service.atualizarLivro(livroAtualizado, id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (service.deletar(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
