package API.nhyira.apivitalis.DTO.Treino;


import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.Exercicio.TagExercicioDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.TagExercicio;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TreinoExibitionDto {

    private Integer idTreino;

    private ExercicioExibition exercicioId;

    private RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaId;

    private Integer serie;

    private Integer repeticao;

    private LocalTime tempo;

    private Integer concluido;



}
