package com.pedrosantos15.alunosapi.controller;

import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.service.AlunoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/alunos")
@RestController
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public void salvar(@RequestBody Aluno aluno){

    }


}
