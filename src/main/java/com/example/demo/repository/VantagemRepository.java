package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Vantagem;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Integer>{
    
}
