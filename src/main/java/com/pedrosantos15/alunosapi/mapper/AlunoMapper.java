package com.pedrosantos15.alunosapi.mapper;

import com.pedrosantos15.alunosapi.dto.AlunoDto;
import com.pedrosantos15.alunosapi.model.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDto alunoToAlunoDto(Aluno aluno);

    Aluno alunoDtoToAluno(AlunoDto alunoDto);
}
