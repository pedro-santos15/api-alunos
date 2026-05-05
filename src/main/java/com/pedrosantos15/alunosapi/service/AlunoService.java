package com.pedrosantos15.alunosapi.service;

import com.pedrosantos15.alunosapi.mapper.AlunoMapper;
import com.pedrosantos15.alunosapi.dto.AlunoDto;
import com.pedrosantos15.alunosapi.exceptions.AlunoNotFound;
import com.pedrosantos15.alunosapi.model.Aluno;
import com.pedrosantos15.alunosapi.repository.AlunoRepository;
import com.pedrosantos15.alunosapi.validator.AlunoValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoService {


    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);
    private final AlunoRepository repository;
    private final AlunoValidator validator;
    private final AlunoMapper mapper;

    public List<AlunoDto> listarTodos(){
        List<Aluno> alunos = repository.findAll();
        if (alunos.isEmpty()){
            logger.warn("Atualmente não há nenhum aluno cadastrado!");
        }

        return alunos.stream()
                .map(mapper::alunoToAlunoDto)
                .toList();
    }

    public AlunoDto buscaPorId(Long id){
        Aluno aluno = repository.findById(id).orElseThrow(() -> new AlunoNotFound("Aluno não encontrado"));
        return mapper.alunoToAlunoDto(aluno);

    }

    public AlunoDto salvar(AlunoDto alunoDto){
        Aluno aluno = mapper.alunoDtoToAluno(alunoDto);
        validator.validarAluno(aluno);
        Aluno alunosalvo = repository.save(aluno);
        logger.info("Aluno salvo!");
        return mapper.alunoToAlunoDto(alunosalvo);
    }

    public AlunoDto atualizar(Long id,AlunoDto alunoDto){
        if (!repository.existsById(id)){
            throw new AlunoNotFound("Aluno não encontrado para atualização");
        }
        Aluno aluno = mapper.alunoDtoToAluno(alunoDto);
        validator.validarAluno(aluno);
        aluno.setId(id);
        repository.save(aluno);
        return mapper.alunoToAlunoDto(aluno);
    }

    public void deletar(Long id){
        Aluno aluno = repository.findById(id).orElseThrow(() -> new AlunoNotFound("Aluno não encontrado"));
        repository.delete(aluno);
        logger.info("Aluno deletado com sucesso!");
    }


}
