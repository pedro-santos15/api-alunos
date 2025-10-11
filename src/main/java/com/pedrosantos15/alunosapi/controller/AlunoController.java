package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.exceptions.IdadeException;
import com.pedrosantos15.alunosapi.exceptions.NomeException;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.service.AlunoService;
import org.springframework.web.bind.annotation.*;

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
    public void salvar(@RequestBody Aluno aluno){
        try {
            alunoService.salvar(aluno);
        } catch (IdadeException | NomeException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoService.listarTodos();
    }
    
    @GetMapping("{id}")
    public Optional<Aluno> buscaPorId(@PathVariable("id") Long id){
        Optional<Aluno> aluno = Optional.empty();
        try {
            aluno = alunoService.buscaPorId(id);
        }catch (AlunoNotFound e){
            System.out.println(e.getMessage());
        }
        
        return aluno;
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") Long id,@RequestBody Aluno aluno){
        alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") Long id){
        alunoService.deletar(id);
    }


}
