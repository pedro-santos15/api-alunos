package com.pedrosantos15.alunosapi.exceptions;

public class AlunoNotFound extends RuntimeException {
    public AlunoNotFound(String message) {
        super(message);
    }
}
