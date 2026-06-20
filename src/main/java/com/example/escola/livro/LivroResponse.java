package com.example.escola.livro;

public class LivroResponse {
    private Long id;
    private String titulo;
    private String autor;

    public LivroResponse(Long id,String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}