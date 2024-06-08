package API.nhyira.apivitalis.DTO.RotinaDiaria;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class RotinaDiariaExibitionDto {

    private Integer idRotinaDiaria;

    private RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalId;

    private Integer concluido;
    private Integer dia;

    private List<TreinoDto> treinoDto;

    private List<RefeicaoDiariaDto> refeicaoDiariaDtos;

    @Data
    public static class TreinoDto{
        private Integer idTreino;

        private Integer serie;

        private Integer repeticao;

        private LocalTime tempo;

        private Integer concluido;
    }

    @Data
    public static class RefeicaoDiariaDto{

        private Integer idRotinaDiaria;

        private Integer concluido;

    }





}
