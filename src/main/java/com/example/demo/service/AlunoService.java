package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.model.Aluno;
import com.example.demo.model.User;
import com.example.demo.model.Vantagem;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VantagemRepository;

@Service
public class AlunoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    VantagemRepository vantagemRepository;

    public List<Transacao> relatorioTransacoes(String emailAluno) {
        User userAluno = userRepository.findByEmail(emailAluno);
        if (userAluno == null || !(userAluno instanceof Aluno)) {
            throw new IllegalArgumentException("Professor não encontrado com o email: " + emailAluno);
        }
        Aluno aluno = (Aluno) userAluno;
        return aluno.getTransacaoList();
    }

    public Vantagem selecionarVantagem(String emailAluno, int vantagemId) {
        User userAluno = userRepository.findByEmail(emailAluno);
        if (userAluno == null || !(userAluno instanceof Aluno)) {
            throw new IllegalArgumentException("Aluno não encontrado com o email: " + emailAluno);
        }
        Aluno aluno = (Aluno) userAluno;


        Vantagem vantagem = vantagemRepository.findById(vantagemId)
                .orElseThrow(() -> new IllegalArgumentException("Vantagem não encontrada com o ID: " + vantagemId));

        if (aluno.getSaldo() < vantagem.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente para selecionar esta vantagem.");
        }

        aluno.setSaldo(aluno.getSaldo() - vantagem.getValor());
        
        Transacao transacao = new Transacao(aluno.getNome(), null, -(vantagem.getValor()));

        aluno.addTransacao(transacao);
        aluno.addVantagens(vantagem);

        transacaoRepository.save(transacao);
        vantagemRepository.save(vantagem);
        userRepository.save(aluno);

        return vantagem;
    }



}