package API.nhyira.apivitalis.DTO.Contrato;

import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;

public class ContratoMapper {



    public static ContratoExibitionDto toDto(Contrato contrato){
        if(contrato == null)return null;
        ContratoExibitionDto contratoExibitionDto = new ContratoExibitionDto();

        contratoExibitionDto.setIdContrato(contrato.getIdContrato());
        contratoExibitionDto.setInicioContrato(contrato.getInicioContrato());
        contratoExibitionDto.setFimContrato(contrato.getFimContrato());
        contratoExibitionDto.setPersonalId(usuarioDto(contrato.getPersonalId()));
        contratoExibitionDto.setUsuarioId(usuarioDto(contrato.getUsuarioId()));
        contratoExibitionDto.setAfiliacao(contrato.getAfiliacao());
        return contratoExibitionDto;
    }


    public static ContratoExibitionDto toDto(Contrato contrato, Meta meta){
        if(contrato == null)return null;
        ContratoExibitionDto contratoExibitionDto = new ContratoExibitionDto();

        contratoExibitionDto.setIdContrato(contrato.getIdContrato());
        contratoExibitionDto.setInicioContrato(contrato.getInicioContrato());
        contratoExibitionDto.setFimContrato(contrato.getFimContrato());
        contratoExibitionDto.setPersonalId(usuarioDto(contrato.getPersonalId(), meta));
        contratoExibitionDto.setUsuarioId(usuarioDto(contrato.getUsuarioId(), meta));
        contratoExibitionDto.setAfiliacao(contrato.getAfiliacao());
        return contratoExibitionDto;
    }

    public static List<ContratoExibitionDto> toDto(List<Contrato> contratoes){
        return contratoes.stream().map(ContratoMapper::toDto).toList();
    }

    public static Contrato toEntity(ContratoCreateEditDto contratoCreateEditDto){
        if(contratoCreateEditDto == null)return null;

        Contrato contrato = new Contrato();
        contrato.setInicioContrato(contratoCreateEditDto.getInicioContrato());
        return contrato;
    }

    public static Contrato toEdit(Contrato contrato, ContratoEditDto contratoEditDto){
        if (contrato == null || contratoEditDto == null)return null;
        contrato.setAfiliacao(contratoEditDto.getAfiliado());
        if(contratoEditDto.getFimContrato() == null){
            contrato.setFimContrato(contrato.getFimContrato());
        }else {
            contrato.setFimContrato(contratoEditDto.getFimContrato());
        }
        return contrato;
    }

    public static ContratoExibitionDto.UsuarioDto usuarioDto(Usuario usuario, Meta meta){
        ContratoExibitionDto.UsuarioDto usuarioDto = new ContratoExibitionDto.UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setMidia(usuarioDto.getMidia());
        usuarioDto.setMeta(meta);
        return usuarioDto;
    }
    public static ContratoExibitionDto.UsuarioDto usuarioDto(Usuario usuario){
        ContratoExibitionDto.UsuarioDto usuarioDto = new ContratoExibitionDto.UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setMidia(usuarioDto.getMidia());
        return usuarioDto;
    }



}
