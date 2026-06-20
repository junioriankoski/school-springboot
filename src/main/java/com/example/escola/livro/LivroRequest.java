package com.example.escola.livro;

public class LivroRequest {
    private String titulo;
    private String autor;

    public LivroRequest() {}

    public LivroRequest(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
