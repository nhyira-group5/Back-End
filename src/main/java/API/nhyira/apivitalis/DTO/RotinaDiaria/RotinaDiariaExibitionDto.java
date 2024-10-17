package API.nhyira.apivitalis.DTO.RotinaDiaria;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class RotinaDiariaExibitionDto {
    private Integer idRotinaDiaria;
    private Integer concluido;
    private Integer dia;
    private Integer totalExercicios;
    private Integer totalExerciciosConcluidos;
    private List<RefeicaoDiariaDto> refeicaoDiariaDtos;
    private RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalId;

    @Data
    public static class RefeicaoDiariaDto{
        private Integer idRefeicaoDiaria;
        private Integer concluido;
    }
}
