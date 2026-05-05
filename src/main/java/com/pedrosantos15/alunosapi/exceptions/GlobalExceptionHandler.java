package com.pedrosantos15.alunosapi.exceptions;

import com.pedrosantos15.alunosapi.dto.ErroCampo;
import com.pedrosantos15.alunosapi.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaErros);
    }

    @ExceptionHandler(AlunoNotFound.class)
    public ResponseEntity<ErroResposta> handleAlunoNotFound(AlunoNotFound e){

        ErroResposta erroResposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of());

        return ResponseEntity.status(erroResposta.status()).body(erroResposta);
    }

    @ExceptionHandler(IdadeException.class)
    public ResponseEntity<ErroResposta> handleIdadeException(IdadeException e){

        ErroResposta erroResposta =
                new ErroResposta(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(), List.of());

        return ResponseEntity.status(erroResposta.status()).body(erroResposta);
    }

    @ExceptionHandler(NomeException.class)
    public ResponseEntity<ErroResposta> handleNomeException(NomeException e){

        ErroResposta erroResposta =
                new ErroResposta(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(), List.of());

        return ResponseEntity.status(erroResposta.status()).body(erroResposta);
    }
}
