package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.exceptions.IdadeException;
import com.pedrosantos15.alunosapi.exceptions.NomeException;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
