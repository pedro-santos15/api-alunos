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
    public ResponseEntity<AlunoDto> buscaPorId(@PathVariable("id") Long id) {
        Aluno aluno = alunoService.buscaPorId(id);

        AlunoDto dto = new AlunoDto(
                aluno.getNome(),
                aluno.getIdade(),
                aluno.getCurso());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id, @RequestBody AlunoDto alunoDto) {

        Aluno aluno = alunoDto.mapearParaAluno();
        alunoService.atualizar(id, aluno);

        return ResponseEntity.ok(aluno);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();
    }


}
