package com.pedrosantos15.alunosapi.dto;

import com.pedrosantos15.alunosapi.model.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDto(
        @NotBlank
        String nome,
        @NotNull
        Integer idade,
        @NotBlank
        String curso) {
}
