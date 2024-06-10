package API.nhyira.apivitalis.DTO.Treino;


import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.TagExercicio;
import API.nhyira.apivitalis.Entity.Treino;

import java.util.List;

public class TreinoMapper {

    public static TreinoExibitionDto toDto(Treino treino){
        if (treino == null)return null;

        TreinoExibitionDto treinoExibitionDto = new TreinoExibitionDto();
        treinoExibitionDto.setIdTreino(treino.getIdTreino());
        treinoExibitionDto.setSerie(treino.getSerie());
        treinoExibitionDto.setTempo(treino.getTempo());
        treinoExibitionDto.setConcluido(treino.getConcluido());
        treinoExibitionDto.setRepeticao(treino.getIdTreino());
        treinoExibitionDto.setRotinaDiariaId(rotinaDiariaDto(treino.getRotinaDiariaId()));
        treinoExibitionDto.setExercicioId(exercicioDto(treino.getExercicioId()));
        return treinoExibitionDto;
    }


    public static List<TreinoExibitionDto> toDto(List<Treino> treinos){
        return treinos.stream().map(TreinoMapper::toDto).toList();
    }

    public static ExercicioExibition exercicioDto(Exercicio exercicio){
        if(exercicio == null)return null;

        ExercicioExibition exercicioDto = new ExercicioExibition();
        exercicioDto.setIdExercicio(exercicio.getIdExercicio());
        exercicioDto.setNome(exercicio.getNome());
        exercicioDto.setDescricao(exercicio.getDescricao());
        exercicioDto.setTagExercicioDtos(tagExibitionDto(exercicio.getTagExercicios()));
        return exercicioDto;
    }


    public static List<ExercicioExibition.TagExibitionDto> tagExibitionDto(List<TagExercicio> tagExercicioList){
        return tagExercicioList.stream().map(te -> {
            ExercicioExibition.TagExibitionDto tagExibitionDto = new ExercicioExibition.TagExibitionDto();

            tagExibitionDto.setIdTagExercicio(te.getIdTagExercicio());
            tagExibitionDto.setTagId(te.getTag());
            return tagExibitionDto;
        }).toList();
    }


    public static RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto(RotinaDiaria rotinaDiaria){
        if (rotinaDiaria == null)return null;
        RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto = new RotinaSemanalExibitionDto.RotinaDiariaDto();
        rotinaDiariaDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaDto.setConcluido(rotinaDiaria.getConcluido());
        return rotinaDiariaDto;
    }
}
