package com.example.escola;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.escola.aluno.Aluno;
import com.example.escola.aluno.AlunoRepository;
import com.example.escola.livro.Livro;
import com.example.escola.livro.LivroRepository;
import com.example.escola.professor.Professor;
import com.example.escola.professor.ProfessorRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private AlunoRepository alunoRepository;
    private LivroRepository livroRepository;
    private ProfessorRepository professorRepository;

    public DataLoader(AlunoRepository alunoRepository, LivroRepository livroRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.livroRepository = livroRepository;
        this.professorRepository = professorRepository;

    }
      @Override
    public void run(String... args) {
        alunoRepository.save(new Aluno("Junior", 20, "IA"));
        alunoRepository.save(new Aluno("Bruno", 20, "Cybersecurity"));

        livroRepository.save(new Livro("A Arte da Guerra", "Sun Tzu"));
        livroRepository.save(new Livro("48 Leis do Poder", "Robert Greene"));
        livroRepository.save(new Livro("O Príncipe", "Nicolau Maquiavel"));

        professorRepository.save(new Professor("Vitor", "Data Cience", "vitor@gmail.com"));
        professorRepository.save(new Professor("Julio", "Redes", "julio@gmail.com"));
    }
}