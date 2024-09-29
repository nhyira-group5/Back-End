package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionDto;
import API.nhyira.apivitalis.Entity.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static Usuario toDto(UsuarioCreateEditDto dto) {
        if (dto == null) return null;
        Usuario user = new Usuario();
        user.setNome(dto.getNome());
        user.setNickname(dto.getNickname());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setSexo(dto.getSexo());
        user.setDtNasc(dto.getDtNasc());
        user.setTipo(dto.getTipo() != null ? Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);
        return user;
    }

    public static UsuarioExibitionDto toExibition(Usuario entity) {
        if (entity == null) return null;
        UsuarioExibitionDto user = new UsuarioExibitionDto();

        user.setId(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNickname(entity.getNickname());
        user.setCpf(entity.getCpf());
        user.setEmail(entity.getEmail());
        user.setSexo(entity.getSexo());
        user.setDtNasc(entity.getDtNasc());
        user.setTipo(entity.getTipo());
        user.setPontos(entity.getPontos());
        user.setMidia(midiaExibitionDto(entity.getMidiaId()));
        user.setPersonalId(usuarioDto(entity.getPersonalId()));
        user.setAcademiaId(endercoDto(entity.getEnderecoId()));
        return user;
    }

    public static UsuarioExibitionDto toExibition(Usuario entity, Meta metaEntity) {
        if (entity == null) return null;
        UsuarioExibitionDto user = new UsuarioExibitionDto();

        user.setId(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNickname(entity.getNickname());
        user.setCpf(entity.getCpf());
        user.setEmail(entity.getEmail());
        user.setSexo(entity.getSexo());
        user.setDtNasc(entity.getDtNasc());
        user.setTipo(entity.getTipo());
        user.setPontos(entity.getPontos());
        user.setMidia(midiaExibitionDto(entity.getMidiaId()));
        user.setPersonalId(usuarioDto(entity.getPersonalId()));
        user.setAcademiaId(endercoDto(entity.getEnderecoId()));
        user.setMeta(toMetaDto(metaEntity));
        return user;
    }


    public static UsuarioExibitionDto.MidiaDto midiaExibitionDto(Midia md){
        if (md == null)return null;
        UsuarioExibitionDto.MidiaDto midiaExibitionDto = new UsuarioExibitionDto.MidiaDto();
        midiaExibitionDto.setIdMidia(md.getIdMidia());
        midiaExibitionDto.setNome(md.getNome());
        midiaExibitionDto.setTipo(md.getTipo());
        midiaExibitionDto.setCaminho(md.getCaminho());
        midiaExibitionDto.setExtensao(md.getExtensao());
        return midiaExibitionDto;
    }


    public static UsuarioExibitionDto toExibition(Usuario entity, Meta metaEntity, boolean pagAtivo) {
        if (entity == null) return null;
        UsuarioExibitionDto user = new UsuarioExibitionDto();

        user.setId(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNickname(entity.getNickname());
        user.setCpf(entity.getCpf());
        user.setEmail(entity.getEmail());
        user.setSexo(entity.getSexo());
        user.setDtNasc(entity.getDtNasc());
        user.setTipo(entity.getTipo());
        user.setPontos(entity.getPontos());
        user.setMidia(midiaExibitionDto(entity.getMidiaId()));
        user.setPersonalId(usuarioDto(entity.getPersonalId()));
        user.setAcademiaId(endercoDto(entity.getEnderecoId()));
        user.setMeta(toMetaDto(metaEntity));
        user.setPagamentoAtivo(pagAtivo);
        return user;
    }

    public static List<UsuarioExibitionDto> toExibition(List<Usuario> entities) {
        if (entities == null) return null;
        return entities.stream().map(e -> toExibition(e)).toList();
    }

    public static UsuarioFichaDto toExibitionIMC(Usuario entity, double imc) {
        if (entity == null) return null;
        UsuarioFichaDto user = new UsuarioFichaDto();
        user.setIdUsuario(entity.getIdUsuario());
        user.setNome(entity.getNome());
        user.setNickname(entity.getNickname());
        user.setCpf(entity.getCpf());
        user.setEmail(entity.getEmail());
        user.setTipo(entity.getTipo());
        user.setDtNasc(entity.getDtNasc());
        user.setSexo(entity.getSexo());
        user.setIMC(imc);
        return user;
    }

    public static Usuario toEditDto(Usuario user, UsuarioCreateEditDto dto) {
        if (dto == null) return null;
        user.setNome(dto.getNome());
        user.setNickname(dto.getNickname());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setSexo(dto.getSexo());
        user.setDtNasc(dto.getDtNasc());
        user.setTipo(dto.getTipo() != null ? Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);
        return user;
    }

    public static Usuario of(UsuarioCreateEditDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNickname(usuarioCriacaoDto.getNickname());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setSexo(usuarioCriacaoDto.getSexo());
        usuario.setDtNasc(usuarioCriacaoDto.getDtNasc());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setTipo(usuarioCriacaoDto.getTipo());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario user, String token) {
        UsuarioTokenDto usuario = new UsuarioTokenDto();
        usuario.setId(user.getIdUsuario());
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setNickname(user.getNickname());
        usuario.setTipo(user.getTipo());
        usuario.setToken(token);

        return usuario;
    }

    public static UsuarioDto usuarioDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setCpf(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setMidiaDto(midiaExibitionDto(usuario.getMidiaId()));
        return usuarioDto;
    }

    public static UsuarioExibitionDto.EnderecoDto endercoDto(Endereco endereco) {
        if (endereco == null) return null;

        UsuarioExibitionDto.EnderecoDto enderecoDto = new UsuarioExibitionDto.EnderecoDto();
        enderecoDto.setId(endereco.getIdEndereco());
        enderecoDto.setLogradouro(endereco.getLogradouro());
        enderecoDto.setBairro(endereco.getBairro());
        enderecoDto.setCidade(endereco.getCidade());
        enderecoDto.setCep(endereco.getCep());
        enderecoDto.setComplemento(endereco.getComplemento());
        enderecoDto.setEstado(endereco.getEstado());
        enderecoDto.setNumero(endereco.getNumero());
        return enderecoDto;
    }

    public static PersonalEspecialidadeDto toDtoPersonal(Usuario entity, List<EspecialidadePorPersonal> especialidadePorPersonal) {
        if (entity == null) return null;

        PersonalEspecialidadeDto personalEspecialidadeDto = new PersonalEspecialidadeDto();
        personalEspecialidadeDto.setIdPersonal(entity.getIdUsuario());
        personalEspecialidadeDto.setNome(entity.getNome());
        personalEspecialidadeDto.setSexo(entity.getSexo());
        personalEspecialidadeDto.setEmail(entity.getEmail());
        personalEspecialidadeDto.setCpf(entity.getCpf());
        personalEspecialidadeDto.setTipo(entity.getTipo());
        personalEspecialidadeDto.setDtNasc(entity.getDtNasc());
        personalEspecialidadeDto.setNickname(entity.getNickname());
        personalEspecialidadeDto.setExibitonDto(especialidadePorPersonalDto(especialidadePorPersonal));
        personalEspecialidadeDto.setMidiaDto(toMidiaDto(entity.getMidiaId()));
        return personalEspecialidadeDto;
    }

    public static List<PersonalEspecialidadeDto.EspecialidadePorPersonalDto> especialidadePorPersonalDto(List<EspecialidadePorPersonal> especialidadePorPersonal) {
        return especialidadePorPersonal.stream().map(ep -> {
            PersonalEspecialidadeDto.EspecialidadePorPersonalDto especialidadePorPersonalDto = new PersonalEspecialidadeDto.EspecialidadePorPersonalDto();
            especialidadePorPersonalDto.setEspecialidadeId(especialidadeDto(ep.getEspecialidadeId()));
            especialidadePorPersonalDto.setId(ep.getIdEspecialidadePersonal());
            return especialidadePorPersonalDto;
        }).toList();
    }

    public static EspecialidadeExibitionDto especialidadeDto(Especialidade especialidade) {
        if (especialidade == null) return null;

        EspecialidadeExibitionDto exibitionDto = new EspecialidadeExibitionDto();
        exibitionDto.setId(especialidade.getIdEspecialidade());
        exibitionDto.setNome(especialidade.getNome());
        return exibitionDto;
    }

    public static RefeicaoExibitionDto.MidiaDto toMidiaDto(Midia midia) {
        if (midia == null) return null;

        RefeicaoExibitionDto.MidiaDto midiaDto = new RefeicaoExibitionDto.MidiaDto();
        midiaDto.setId(midia.getIdMidia());
        midiaDto.setNome(midia.getNome());
        midiaDto.setCaminho(midia.getCaminho());
        midiaDto.setExtensao(midia.getExtensao());
        return midiaDto;
    }

    public static UsuarioExibitionDto.MetaDto toMetaDto(Meta meta) {
        if (meta == null) return null;

        UsuarioExibitionDto.MetaDto metaDto = new UsuarioExibitionDto.MetaDto();
        metaDto.setId(meta.getIdMeta());
        metaDto.setNome(meta.getNome());
        return metaDto;
    }
}
