package API.nhyira.apivitalis.DTO.RotinaSemanal;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;

import java.util.List;

public class RotinaSemanalMapper {

    public static RotinaSemanalExibitionDto toDto(RotinaSemanal rotinaSemanal){
        if (rotinaSemanal == null)return null;
        RotinaSemanalExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalExibitionDto();
        rotinaSemanalExibitionDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalExibitionDto.setConcluido(rotinaSemanal.getConcluido());
        rotinaSemanalExibitionDto.setRotinaMensalId(rotinaMensalDto(rotinaSemanal.getRotinaMensalId()));
        return rotinaSemanalExibitionDto;
    }


    public static RotinaSemanalExibitionDto.RotinaMensalExibitionDto rotinaMensalDto(RotinaMensal rotinaMensal){
            RotinaSemanalExibitionDto.RotinaMensalExibitionDto rotinaSemanalExibitionDto = new RotinaSemanalExibitionDto.RotinaMensalExibitionDto();
            rotinaSemanalExibitionDto.setId(rotinaMensal.getIdRotinaMensal());
            rotinaSemanalExibitionDto.setAno(rotinaMensal.getAno());
            rotinaSemanalExibitionDto.setMes(rotinaMensal.getMes());
            return rotinaSemanalExibitionDto;
    }


}
