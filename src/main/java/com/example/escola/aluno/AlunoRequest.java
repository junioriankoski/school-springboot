package com.example.escola.aluno;

public class AlunoRequest {
    private String nome;
    private String curso;
    private int idade;

    public AlunoRequest() {}

    public AlunoRequest(String nome, String curso, int idade) {
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public int getIdade() {
        return idade;
    }

}
