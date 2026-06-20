package com.example.escola.professor;

public class ProfessorRequest {
    private String nome;
    private String especialidade;
    private String email;

    public ProfessorRequest() {}

    public ProfessorRequest(String nome, String especialidade, String email) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getEmail() {
        return email;
    }
    
}
