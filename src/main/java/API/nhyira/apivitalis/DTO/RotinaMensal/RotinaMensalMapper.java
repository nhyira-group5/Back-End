package API.nhyira.apivitalis.DTO.RotinaMensal;

import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioMapper;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaUsuario;

public class RotinaMensalMapper {

    public static RotinaMensalExibitionDto toDto(RotinaMensal rotinaMensal){
        if (rotinaMensal == null)return null;

        RotinaMensalExibitionDto exibitionDto = new RotinaMensalExibitionDto();
        exibitionDto.setId(rotinaMensal.getIdRotinaMensal());
        exibitionDto.setMes(rotinaMensal.getMes());
        exibitionDto.setRotinaUsuarioId(rotinaUsuarioDto(rotinaMensal.getRotinaUsuarioId()));
        exibitionDto.setAno(rotinaMensal.getIdRotinaMensal());
        return exibitionDto;
    }


    public static RotinaMensalExibitionDto.RotinaUsuarioDto rotinaUsuarioDto(RotinaUsuario rotinaUsuario){
        if (rotinaUsuario == null)return null;
        RotinaMensalExibitionDto.RotinaUsuarioDto rotinaUsuarioDto = new RotinaMensalExibitionDto.RotinaUsuarioDto();
        rotinaUsuarioDto.setId(rotinaUsuario.getIdRotinaUsuario());
        rotinaUsuarioDto.setMetaId(rotinaUsuario.getMetaId());
        return rotinaUsuarioDto;
    }


}
