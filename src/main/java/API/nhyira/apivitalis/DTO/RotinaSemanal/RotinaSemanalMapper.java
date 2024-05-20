package API.nhyira.apivitalis.DTO.RotinaSemanal;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.RotinaUsuario;

import java.util.List;

public class RotinaSemanalMapper {

    public static RotinaSemanalExibitionDto toDto(RotinaSemanal rotinaSemanal){
        if (rotinaSemanal == null)return null;

        RotinaSemanalExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalExibitionDto();
        rotinaSemanalExibitionDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalExibitionDto.setConcluido(rotinaSemanal.getConcluida());
        rotinaSemanalExibitionDto.setRotinaMensalId(rotinaMensalDto(rotinaSemanal.getRotinaMensalId()));
        return rotinaSemanalExibitionDto;
    }

    public static List<RotinaSemanalExibitionDto> toDto(List<RotinaSemanal> rotinaSemanal){
        return rotinaSemanal.stream().map(RotinaSemanalMapper::toDto).toList();

    }

    public static RotinaSemanalExibitionDto.RotinaMensalDto rotinaMensalDto(RotinaMensal rotinaMensal){
        if (rotinaMensal == null)return null;
        RotinaSemanalExibitionDto.RotinaMensalDto rotinaMensalDto = new RotinaSemanalExibitionDto.RotinaMensalDto();
        rotinaMensalDto.setId(rotinaMensal.getIdRotinaMensal());
        rotinaMensalDto.setMes(rotinaMensal.getMes());
        rotinaMensalDto.setAno(rotinaMensalDto.getAno());
        return rotinaMensalDto;
    }

}
