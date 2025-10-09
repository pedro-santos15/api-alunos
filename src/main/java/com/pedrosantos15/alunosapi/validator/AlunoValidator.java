package com.pedrosantos15.alunosapi.validator;

import com.pedrosantos15.alunosapi.exceptions.IdadeException;
import com.pedrosantos15.alunosapi.exceptions.NomeException;
import com.pedrosantos15.alunosapi.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoValidator {

    public void validarNome(String nome) {
        if (!nome.matches("^[A-Za-zÀ-ú]+\\s[A-Za-zÀ-ú]+(?:\\s[A-Za-zÀ-ú]+)*$")) {
            throw new NomeException("Este nome não é permitido, favor informar um nome válido");
        }
    }

    public void validarIdade(int idade) {
        if (idade < 18 || idade > 80) {
            throw new IdadeException("Idade não permitida! Favor informar um idade valida");
        }
    }

    public void validarAluno(Aluno aluno) {
        validarNome(aluno.getNome());
        validarIdade(aluno.getIdade());
    }
}
