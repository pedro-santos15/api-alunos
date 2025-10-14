package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.exceptions.IdadeException;
import com.pedrosantos15.alunosapi.exceptions.NomeException;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/alunos") // -> Isso faz a criaçaõ de um endpoint
@RestController
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno){
        Aluno alunoSalvo = null;
        try {
            alunoSalvo = alunoService.salvar(aluno);
        } catch (IdadeException | NomeException e){
            System.out.println(e.getMessage());
        }
        return alunoSalvo;
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
    public Aluno atualizar(@PathVariable("id") Long id,@RequestBody Aluno aluno){
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") Long id){
        alunoService.deletar(id);
    }


}
