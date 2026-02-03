package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.controller.dto.ErroResposta;
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
            alunoService.salvar(aluno);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(aluno.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (IdadeException | NomeException e) {
            ErroResposta erroResposta = ErroResposta.mensagemPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> buscaPorId(@PathVariable("id") Long id) {
        try {
            Optional<Aluno> aluno = alunoService.buscaPorId(id);

            AlunoDto dto = new AlunoDto(aluno.get().getNome(),
                    aluno.get().getIdade(),
                    aluno.get().getCurso());

            return ResponseEntity.ok(dto);

        } catch (AlunoNotFound e) {

            ErroResposta erroResposta = ErroResposta.mensagemPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id, @RequestBody AlunoDto alunoDto) {
        try {
            Optional<Aluno> optional = alunoService.buscaPorId(id);
            Aluno aluno = alunoDto.mapearParaAluno();
            alunoService.atualizar(id, aluno);

            return ResponseEntity.ok(aluno);

        } catch (AlunoNotFound e){
            ErroResposta erroResposta = ErroResposta.mensagemPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
        try{
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();

        } catch (AlunoNotFound e){

            ErroResposta erroResposta = ErroResposta.mensagemPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }


}
