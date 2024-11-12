package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
