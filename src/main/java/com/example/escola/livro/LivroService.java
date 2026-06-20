package com.example.escola.livro;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    
    private LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public List<LivroResponse> listarTodos() {
        return repository.findAll()
            .stream()
            .map(livro -> new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor()
            ))
            .toList();
    }

    public Optional<LivroResponse> buscarPorId(Long id) {
        return repository.findById(id)
            .map(livro -> new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor()
            ));
    }

    public LivroResponse salvar(LivroRequest livro) {
        Livro novoLivro = new Livro(livro.getTitulo(),livro.getAutor());
        Livro salvo = repository.save(novoLivro);
        return new LivroResponse(
            salvo.getId(),
            salvo.getTitulo(),
            salvo.getAutor()
        );
    }

    public Optional<LivroResponse> atualizarLivro(LivroRequest livroAtualizado, Long id) {
        return repository.findById(id)
            .map(livro -> {
                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setAutor(livroAtualizado.getAutor());
                Livro salvo = repository.save(livro);
                return new LivroResponse(
                    salvo.getId(), 
                    salvo.getTitulo(), 
                    salvo.getAutor());
            });
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
