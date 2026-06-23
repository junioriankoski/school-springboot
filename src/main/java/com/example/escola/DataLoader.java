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
        if(professorRepository.count()>0) {
            return;
        }
        Professor vitor = professorRepository.save(new Professor("Vitor", "Data Cience", "vitor@gmail.com"));
        Professor julio = professorRepository.save(new Professor("Julio", "Computer Networks", "julio@gmail.com"));
        Professor roberto = professorRepository.save(new Professor("Roberto","Ética","roberto@gmai.com"));

        Aluno junior = new Aluno("Junior", 20, "IA");
        junior.setProfessor(vitor);
        alunoRepository.save(junior);
        
        Aluno bruno = new Aluno("Bruno", 20, "Cybersecurity");
        bruno.setProfessor(julio);
        alunoRepository.save(bruno);

        Aluno ana = new Aluno("Ana",22,"ADS");
        ana.setProfessor(roberto);
        alunoRepository.save(ana);
        
        livroRepository.save(new Livro("A Arte da Guerra", "Sun Tzu"));
        livroRepository.save(new Livro("48 Leis do Poder", "Robert Greene"));
        livroRepository.save(new Livro("O Príncipe", "Nicolau Maquiavel"));
    }
}