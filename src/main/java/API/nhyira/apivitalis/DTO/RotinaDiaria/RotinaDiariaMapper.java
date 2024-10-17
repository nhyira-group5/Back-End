package API.nhyira.apivitalis.DTO.RotinaDiaria;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;

import java.util.List;

public class RotinaDiariaMapper {
    public static RotinaDiariaExibitionDto toDto(RotinaDiaria rotinaDiaria, Integer totalExercicios,
                                                 Integer totalExerciciosFeitos) {
        if (rotinaDiaria == null || (totalExercicios == null || totalExercicios < 0) || (totalExerciciosFeitos == null || totalExerciciosFeitos < 0)) return null;
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

    public static List<RotinaDiariaExibitionDto> toDto(List<RotinaDiaria> rotinaDiarias, Integer totalExercicios,
                                                       Integer totalExerciciosFeitos) {
        if (rotinaDiarias == null || (totalExercicios == null || totalExercicios < 0) || (totalExerciciosFeitos == null || totalExerciciosFeitos < 0))
            return null;
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
        if (refeicaoDiarias == null) return null;
        return refeicaoDiarias.stream().map(rd -> {
            RotinaDiariaExibitionDto.RefeicaoDiariaDto refeicaoDiariaDto = new RotinaDiariaExibitionDto.RefeicaoDiariaDto();
            refeicaoDiariaDto.setConcluido(rd.getConcluido());
            refeicaoDiariaDto.setIdRefeicaoDiaria(rd.getIdRefeicaoDiaria());
            return refeicaoDiariaDto;
        }).toList();
    }
}
