package API.nhyira.apivitalis.DTO.RotinaSemanal;

import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;

import java.util.List;

public class RotinaSemanalMapper {
    public static RotinaSemanalExibitionDto toDto(RotinaSemanal rotinaSemanal) {
        if (rotinaSemanal == null) return null;

        RotinaSemanalExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalExibitionDto();
        rotinaSemanalExibitionDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalExibitionDto.setNumSemana(rotinaSemanal.getNumSemana());
        rotinaSemanalExibitionDto.setConcluido(rotinaSemanal.getConcluido());
        rotinaSemanalExibitionDto.setRotinaMensalId(rotinaMensalDto(rotinaSemanal.getRotinaMensalId()));
        rotinaSemanalExibitionDto.setRotinaDiariaDtos(rotinaDiariaDto(rotinaSemanal.getRotinaDiariaId()));
        return rotinaSemanalExibitionDto;
    }
    public static List<RotinaSemanalExibitionDto> toDto(List<RotinaSemanal> rotinaSemanals) {
        return rotinaSemanals.stream().map(RotinaSemanalMapper::toDto).toList();
    }

    public static RotinaSemanalListExibitionDto toDtoList(RotinaSemanal rotinaSemanal) {
        if (rotinaSemanal == null) return null;
        RotinaSemanalListExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalListExibitionDto();
        rotinaSemanalExibitionDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalExibitionDto.setNumSemana(rotinaSemanal.getNumSemana());
        rotinaSemanalExibitionDto.setConcluido(rotinaSemanal.getConcluido());
        rotinaSemanalExibitionDto.setRotinaMensalId(rotinaMensalDto(rotinaSemanal.getRotinaMensalId()));
        return rotinaSemanalExibitionDto;
    }
    public static List<RotinaSemanalListExibitionDto> toDtos(List<RotinaSemanal> rotinaSemanals) {
        return rotinaSemanals.stream().map(RotinaSemanalMapper::toDtoList).toList();
    }

    public static RotinaSemanalExibitionDto.RotinaMensalExibitionDto rotinaMensalDto(RotinaMensal rotinaMensal) {
        RotinaSemanalExibitionDto.RotinaMensalExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalExibitionDto.RotinaMensalExibitionDto();
        rotinaSemanalExibitionDto.setId(rotinaMensal.getIdRotinaMensal());
        rotinaSemanalExibitionDto.setAno(rotinaMensal.getAno());
        rotinaSemanalExibitionDto.setMes(rotinaMensal.getMes());
        return rotinaSemanalExibitionDto;
    }

    public static List<RotinaSemanalExibitionDto.RotinaDiariaDto> rotinaDiariaDto(List<RotinaDiaria> rotinaDiarias) {
        return rotinaDiarias.stream().map(rd -> {
            RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto = new RotinaSemanalExibitionDto.RotinaDiariaDto();
            rotinaDiariaDto.setIdRotinaDiaria(rd.getIdRotinaDiaria());
            rotinaDiariaDto.setConcluido(rd.getConcluido());
            return rotinaDiariaDto;
        }).toList();
    }
}
