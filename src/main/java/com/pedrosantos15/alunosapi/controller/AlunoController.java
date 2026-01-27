package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.exceptions.IdadeException;
import com.pedrosantos15.alunosapi.exceptions.NomeException;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.controller.dto.AlunoDto;
import com.pedrosantos15.alunosapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/alunos")
@RestController
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AlunoDto alunoDto) {
        try {
            Aluno aluno = alunoDto.mapearParaAluno();
            Aluno alunoSalvo = alunoService.salvar(aluno);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(aluno.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (IdadeException | NomeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodos();
    }

    @GetMapping("{id}")
    public Optional<Aluno> buscaPorId(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = Optional.empty();
        try {
            aluno = alunoService.buscaPorId(id);
        } catch (AlunoNotFound e) {
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    @PutMapping("{id}")
    public Aluno atualizar(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") Long id) {
        alunoService.deletar(id);
    }


}
