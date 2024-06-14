package API.nhyira.apivitalis.DTO.RotinaDiaria;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;

import java.util.List;

public class RotinaDiariaMapper {

    public static RotinaDiariaExibitionDto toDto(RotinaDiaria rotinaDiaria, Integer totalExercicios,
            Integer totalExerciciosFeitos) {
        RotinaDiariaExibitionDto rotinaDiariaExibitionDto = new RotinaDiariaExibitionDto();

        rotinaDiariaExibitionDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaExibitionDto.setDia(rotinaDiaria.getDia());
        rotinaDiariaExibitionDto.setConcluido(rotinaDiaria.getConcluido());
        rotinaDiariaExibitionDto.setTotalExercicios(totalExercicios);
        rotinaDiariaExibitionDto.setTotalExerciciosConcluidos(totalExerciciosFeitos);
        rotinaDiariaExibitionDto.setRotinaSemanalId(rotinaSemanalDto(rotinaDiaria.getRotinaSemanalId()));
        rotinaDiariaExibitionDto.setRefeicaoDiariaDtos(refeicaoDiariaDtos(rotinaDiaria.getRefeicaoDiariaId()));
        return rotinaDiariaExibitionDto;
    }

    public static List<RotinaDiariaExibitionDto> toDtos(List<RotinaDiaria> rotinaDiarias, Integer totalExercicios,
            Integer totalExerciciosFeitos) {
        return rotinaDiarias.stream().map(rd -> toDto(rd, totalExercicios, totalExerciciosFeitos)).toList();
    }

    public static RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto(RotinaSemanal rotinaSemanal) {
        if (rotinaSemanal == null)
            return null;
        RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto = new RotinaMensalExibitionDto.RotinaSemanalDto();
        rotinaSemanalDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalDto.setConcluido(rotinaSemanal.getConcluido());
        return rotinaSemanalDto;
    }

    public static List<RotinaDiariaExibitionDto.RefeicaoDiariaDto> refeicaoDiariaDtos(
            List<RefeicaoDiaria> refeicaoDiarias) {
        return refeicaoDiarias.stream().map(rd -> {
            RotinaDiariaExibitionDto.RefeicaoDiariaDto refeicaoDiariaDto = new RotinaDiariaExibitionDto.RefeicaoDiariaDto();
            refeicaoDiariaDto.setConcluido(rd.getConcluido());
            refeicaoDiariaDto.setIdRotinaDiaria(rd.getIdRefeicaoDiaria());
            return refeicaoDiariaDto;
        }).toList();
    }
}
