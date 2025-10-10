package com.pedrosantos15.alunosapi.service;

import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.repository.AlunoRepository;
import com.pedrosantos15.alunosapi.validator.AlunoValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AlunoService {


    private static final Logger logger = Logger.getLogger(AlunoService.class.getName());
    private AlunoRepository repository;
    private AlunoValidator validator;

    public AlunoService(AlunoRepository repository, AlunoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Aluno> listarTodos(){
        List<Aluno> alunos = repository.findAll();
        if (alunos.isEmpty()){
            logger.warning("Atualmente não há nenhum aluno cadastrado!");
        }
        return alunos;
    }

    public Optional<Aluno> buscaPorId(Long id){
        if (repository.findById(id).isEmpty()){
            logger.warning("Aluno não foi encontrado");
        }
        return repository.findById(id);
    }

    public Aluno salvar(Aluno aluno){
        validator.validarAluno(aluno);
        repository.save(aluno);
        logger.info("Aluno salvo!");
        return aluno;
    }

    public Aluno atualizar(Aluno aluno){
        validator.validarAluno(aluno);
        repository.save(aluno);
        logger.info("Aluno atualizado!");
        return aluno;
    }

    public void deletar(Long id){
        if (repository.findById(id).isEmpty()){
            logger.warning("Usuário não encontrado!");
            return;
        }

        repository.deleteById(id);
        logger.info("Aluno deletado com sucesso!");
    }


}
