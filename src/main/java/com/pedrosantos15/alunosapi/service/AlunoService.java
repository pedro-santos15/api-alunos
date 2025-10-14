package com.pedrosantos15.alunosapi.service;

import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.repository.AlunoRepository;
import com.pedrosantos15.alunosapi.validator.AlunoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {


    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);
    private AlunoRepository repository;
    private AlunoValidator validator;

    public AlunoService(AlunoRepository repository, AlunoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Aluno> listarTodos(){
        List<Aluno> alunos = repository.findAll();
        if (alunos.isEmpty()){
            logger.warn("Atualmente não há nenhum aluno cadastrado!");
        }
        return alunos;
    }

    public Optional<Aluno> buscaPorId(Long id){
        Optional<Aluno> aluno = repository.findById(id);

        if (aluno.isEmpty()){
            throw new AlunoNotFound("Aluno não encontrado");
        }
        return aluno;
    }

    public Aluno salvar(Aluno aluno){
        validator.validarAluno(aluno);
        Aluno alunosalvo = repository.save(aluno);
        logger.info("Aluno salvo!");
        return alunosalvo;
    }

    public Aluno atualizar(Long id,Aluno aluno){
        if (!repository.existsById(id)){
            throw new AlunoNotFound("Aluno não encontrado para atualização");
        }
        validator.validarAluno(aluno);
        aluno.setId(id);
        return repository.save(aluno);
    }

    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw new AlunoNotFound("Aluno não encontrada para a deleção");
        }
        repository.deleteById(id);
        logger.info("Aluno deletado com sucesso!");
    }


}
