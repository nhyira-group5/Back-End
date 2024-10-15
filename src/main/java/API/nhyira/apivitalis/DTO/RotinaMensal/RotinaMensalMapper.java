package API.nhyira.apivitalis.DTO.RotinaMensal;

import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.RotinaUsuario;

import java.util.List;

public class RotinaMensalMapper {
    public static RotinaMensalExibitionDto toDto(RotinaMensal rotinaMensal) {
        if (rotinaMensal == null) return null;

        RotinaMensalExibitionDto exibitionDto = new RotinaMensalExibitionDto();
        exibitionDto.setId(rotinaMensal.getIdRotinaMensal());
        exibitionDto.setMes(rotinaMensal.getMes());
        exibitionDto.setConcluido(rotinaMensal.getConcluido());
        exibitionDto.setRotinaUsuarioId(rotinaUsuarioDto(rotinaMensal.getRotinaUsuarioId()));
        exibitionDto.setAno(rotinaMensal.getAno());
        exibitionDto.setRotinaSemanalDtos(rotinaSemanalDto(rotinaMensal.getRotinaSemanals()));
        return exibitionDto;
    }

    public static RotinaMensalExibitionDto.RotinaUsuarioDto rotinaUsuarioDto(RotinaUsuario rotinaUsuario) {
        if (rotinaUsuario == null) return null;
        RotinaMensalExibitionDto.RotinaUsuarioDto rotinaUsuarioDto = new RotinaMensalExibitionDto.RotinaUsuarioDto();
        rotinaUsuarioDto.setId(rotinaUsuario.getIdRotinaUsuario());
        rotinaUsuarioDto.setMetaId(rotinaUsuario.getMetaId());
        return rotinaUsuarioDto;
    }

    public static List<RotinaMensalExibitionDto.RotinaSemanalDto> rotinaSemanalDto(List<RotinaSemanal> rotinaSemanal) {
        if (rotinaSemanal == null) return null;
        return rotinaSemanal.stream().map(rs -> {
            RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto = new RotinaMensalExibitionDto.RotinaSemanalDto();
            rotinaSemanalDto.setId(rs.getIdRotinaSemanal());
            rotinaSemanalDto.setConcluido(rs.getConcluido());
            return rotinaSemanalDto;
        }).toList();
    }
}
