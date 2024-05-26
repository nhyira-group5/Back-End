package API.nhyira.apivitalis.DTO.Treino;


import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TreinoExibitionDto {

    private Integer idTreino;

    private ExercicioDto exercicioId;

    private RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaId;

    private Integer serie;

    private Integer repeticao;

    private LocalTime tempo;

    private Integer concluido;

    @Data
    public static class ExercicioDto{
        private Integer idExercicio;
        private String nome;
        private String descricao;
    }


}
