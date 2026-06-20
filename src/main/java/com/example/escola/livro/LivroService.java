package com.example.escola.livro;

import org.springframework.stereotype.Service;

import com.example.escola.exception.RecursosNaoEncontradosException;

import java.util.List;

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

    public LivroResponse buscarPorId(Long id) {
        Livro livro = repository.findById(id)
            .orElseThrow(() -> new RecursosNaoEncontradosException("Livro não encontrado"));

        return new LivroResponse(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor()
        );
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

    public LivroResponse atualizarLivro(LivroRequest livroAtualizado, Long id) {
        Livro livro = repository.findById(id)
            .orElseThrow(() -> new RecursosNaoEncontradosException("Livro não encontrado"));
                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setAutor(livroAtualizado.getAutor());
                Livro salvo = repository.save(livro);
            return new LivroResponse(
                salvo.getId(), 
                salvo.getTitulo(), 
                salvo.getAutor()
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
