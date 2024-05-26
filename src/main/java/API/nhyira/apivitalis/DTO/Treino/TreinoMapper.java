package API.nhyira.apivitalis.DTO.Treino;


import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.Treino;

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

    public static TreinoExibitionDto.ExercicioDto exercicioDto(Exercicio exercicio){
        if(exercicio == null)return null;

        TreinoExibitionDto.ExercicioDto exercicioDto = new TreinoExibitionDto.ExercicioDto();
        exercicioDto.setIdExercicio(exercicio.getIdExercicio());
        exercicioDto.setNome(exercicio.getNome());
        exercicioDto.setDescricao(exercicio.getDescricao());
        return exercicioDto;

    }

    public static RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto(RotinaDiaria rotinaDiaria){
        if (rotinaDiaria == null)return null;
        RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto = new RotinaSemanalExibitionDto.RotinaDiariaDto();
        rotinaDiariaDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaDto.setConcluido(rotinaDiaria.getConcluido());
        return rotinaDiariaDto;
    }
}
