package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.mapper.AlunoMapper;
import com.pedrosantos15.alunosapi.dto.AlunoDto;
import com.pedrosantos15.alunosapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/alunos")
@RestController
public class AlunoController {

    private AlunoService alunoService;
    private AlunoMapper mapper;

    public AlunoController(AlunoService alunoService, AlunoMapper mapper) {
        this.alunoService = alunoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AlunoDto alunoDto) {
        alunoService.salvar(alunoDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mapper.alunoDtoToAluno(alunoDto).getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping
    public List<AlunoDto> listarAlunos() {
        return alunoService.listarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoDto> buscaPorId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(alunoService.buscaPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable("id") Long id, @RequestBody AlunoDto alunoDto) {

        return ResponseEntity.ok(alunoService.atualizar(id, alunoDto));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlunoDto> deletar(@PathVariable("id") Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
