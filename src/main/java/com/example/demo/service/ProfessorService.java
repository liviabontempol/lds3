package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.Professor;
import com.example.demo.model.Transacao;
import com.example.demo.model.User;
import com.example.demo.model.Aluno;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProfessorService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    public int depositarSaldo(String emailProfessor, String emailAluno, int valor){
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }

        User userProfessor = userRepository.findByEmail(emailProfessor);
        if (userProfessor == null || !(userProfessor instanceof Professor)) {
            throw new IllegalArgumentException("Professor não encontrado com o email: " + emailProfessor);
        }
        Professor professor = (Professor) userProfessor;


        User userAluno = userRepository.findByEmail(emailAluno);
        if (userAluno == null || !(userAluno instanceof Aluno)) {
            throw new IllegalArgumentException("Aluno não encontrado com o email: " + emailAluno);
        }
        Aluno aluno = (Aluno) userAluno;

        if (professor.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência.");
        }

        professor.setSaldo(professor.getSaldo() - valor);
        aluno.setSaldo(aluno.getSaldo() + valor);

        Transacao transacao = new Transacao(aluno.getNome(), professor.getNome(), valor);

        aluno.addTransacao(transacao);
        professor.addTransacao(transacao);
        
        transacaoRepository.save(transacao);
        userRepository.save(professor);
        userRepository.save(aluno);

        return professor.getSaldo();
    }

    public List<Transacao> relatorioTransacoes(String emailProfessor) {
        User userProfessor = userRepository.findByEmail(emailProfessor);
        if (userProfessor == null || !(userProfessor instanceof Professor)) {
            throw new IllegalArgumentException("Professor não encontrado com o email: " + emailProfessor);
        }
        Professor professor = (Professor) userProfessor;
        return professor.getTransacaoList();
    }

    @Scheduled(cron = "0 0 0 1 1,7 *")
    public void adicionarSaldoSemestral() {
        List<Professor> professores = userRepository.findAll()
                .stream()
                .filter(user -> user instanceof Professor)
                .map(user -> (Professor) user)
                .toList();

        for (Professor professor : professores) {
            professor.setSaldo(professor.getSaldo() + 1000);
            userRepository.save(professor);
            System.out.println("Saldo atualizado para professor: " + professor.getNome() + ", Novo saldo: " + professor.getSaldo());
        }
    }

    // @PostConstruct // Chama automaticamente após a inicialização do contexto Spring
    // public void generateProfessors() {
    //     // Lista de emails de instituições
    //     String[] instituicoes = {
    //             "pucminas@gmail.com",
    //             "ifmg@gmail.com",
    //             "ufmg@gmail.com",
    //             "sesi@gmail.com"
    //     };

    //     // Gerar 20 professores
    //     List<Professor> professores = new ArrayList<>();
    //     for (int i = 0; i < 20; i++) {
    //         String nome = "Professor" + (i + 1);
    //         String email = "professor" + (i + 1) + "@example.com";
    //         String password = "senha123";
    //         String endereco = "Endereço " + (i + 1);

    //         // Alterna entre os emails de instituições
    //         String instituicao = instituicoes[i % instituicoes.length];

    //         String encryptedPassword = new BCryptPasswordEncoder().encode(password);

    //         // Cria o objeto Professor
    //         Professor professor = new Professor(nome, email, encryptedPassword, endereco, instituicao);
    //         professores.add(professor);
    //     }

    //     // Salvar no banco de dados
    //     userRepository.saveAll(professores);

    //     System.out.println("Professores gerados com sucesso!");
    // }
    
}