package API.nhyira.apivitalis.DTO.RotinaDiario;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.RotinaDiaria;

public class RotinaDiarioMapper {

    public static RotinaDiarioExibitionDto toDto(RotinaDiaria rotinaDiaria){
        RotinaDiarioExibitionDto rotinaDiarioExibitionDto = new RotinaDiarioExibitionDto();

        rotinaDiarioExibitionDto.setIdTreinoDiario(rotinaDiaria.getIdTreinoDiario());
        rotinaDiarioExibitionDto.setConcluido(rotinaDiaria.getConcluido());
        rotinaDiarioExibitionDto.setRotinaSemanalId(rotinaSemanalDto(rotinaDiaria.getRotinaSemanalId()));
        return rotinaDiarioExibitionDto;
    }


    public static RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto(RotinaSemanal rotinaSemanal){
        if (rotinaSemanal == null)return null;
        RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto = new RotinaMensalExibitionDto.RotinaSemanalDto();
        rotinaSemanalDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalDto.setConcluido(rotinaSemanal.getConcluido());
        return rotinaSemanalDto;
    }
}
