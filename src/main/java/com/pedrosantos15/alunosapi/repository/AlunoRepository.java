package com.pedrosantos15.alunosapi.repository;

import com.pedrosantos15.alunosapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
