package com.example.escola.professor;

public class ProfessorResponse {
    private Long id;
    private String nome;
    private String especialidade;

    public ProfessorResponse(Long id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
