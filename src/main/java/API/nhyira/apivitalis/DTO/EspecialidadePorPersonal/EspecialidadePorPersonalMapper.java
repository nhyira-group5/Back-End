package API.nhyira.apivitalis.DTO.EspecialidadePorPersonal;

import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.EspecialidadePorPersonal;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;

public class EspecialidadePorPersonalMapper {

    public static EspecialidadePorPersonal toEntity(EspecialidadePorPersonalCreateEditDto dto){
        if (dto == null)return null;
        EspecialidadePorPersonal especialidadePorPersonal = new EspecialidadePorPersonal();
        return especialidadePorPersonal;

    }

    public static EspecialidadePorPersonalExibitonDto toDto(EspecialidadePorPersonal especialidadePorPersonal){
        if (especialidadePorPersonal == null)return null;

        EspecialidadePorPersonalExibitonDto exibitonDto = new EspecialidadePorPersonalExibitonDto();
        exibitonDto.setIdEspecialidade(especialidadePorPersonal.getIdEspecialidadePersonal());
        exibitonDto.setPersonalId(usuarioDto(especialidadePorPersonal.getPersonalId()));
        exibitonDto.setEspecialidadeId(especialidadeDto(especialidadePorPersonal.getEspecialidadeId()));
        return exibitonDto;
    }

    public static EspecialidadeExibitionDto especialidadeDto(Especialidade especialidade){
        if (especialidade== null)return null;
        EspecialidadeExibitionDto exibitionDto = new EspecialidadeExibitionDto();
        exibitionDto.setId(especialidade.getIdEspecialidade());
        exibitionDto.setNome(especialidade.getNome());
        return exibitionDto;
    }

    public static UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null)return null;
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        return usuarioDto;
    }

    public static List<EspecialidadePorPersonalExibitonDto> toDto(List<EspecialidadePorPersonal> entities){
        return entities.stream().map(EspecialidadePorPersonalMapper::toDto).toList();
    }

    public static EspecialidadePorPersonal toEdit(EspecialidadePorPersonal especialidadePorPersonal, EspecialidadePorPersonalCreateEditDto dto){
        if (dto == null)return null;
        return especialidadePorPersonal;
    }
}
