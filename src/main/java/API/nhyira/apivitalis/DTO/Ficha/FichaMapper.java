package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class FichaMapper {

    public static Ficha toEntity(FichaCreateEditDto dto){
        if (dto == null)return null;

        Ficha ficha = new Ficha();
        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setPeso(dto.getPeso());
        ficha.setProblemasCardiacos(dto.getProblemasCardiacos());
        ficha.setDorPeitoAtividade(dto.getDorPeitoAtividade());
        ficha.setDorPeitoUltimoMes(dto.getDorPeitoUltimoMes());
        ficha.setProblemasCardiacos(dto.getProblemasCardiacos());
        ficha.setFuma(dto.getFuma());
        ficha.setMedicamentoPressaoCoracao(dto.getMedicamentoPressaoCoracao());
        ficha.setImpedimentoAtividade(dto.getImpedimentoAtividade());
        ficha.setProblemaOsseoArticular(ficha.getProblemaOsseoArticular());
        return ficha;
    }
    public static List<FichaExibitionDto> toDtoList(List<Ficha> fichas) {
        return fichas.stream()
                .map(FichaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static FichaExibitionDto toDto(Ficha entity){
        if (entity == null)return null;

        FichaExibitionDto user = new FichaExibitionDto();
        user.setId(entity.getIdFicha());
        user.setAltura(entity.getAltura());
        user.setBebe(entity.getBebe());
        user.setProblemasCardiacos(entity.getProblemasCardiacos());
        user.setDorPeitoAtividade(entity.getDorPeitoAtividade());
        user.setPeso(entity.getPeso());
        user.setFuma(entity.getFuma());
        user.setDorPeitoUltimoMes(entity.getDorPeitoUltimoMes());
        user.setPerdaConsiencia(user.getPerdaConsiencia());
        user.setProblemaOsseoArticular(entity.getProblemaOsseoArticular());
        user.setMedicamentoPressaoCoracao(entity.getMedicamentoPressaoCoracao());
        user.setImpedimentoAtividade(entity.getImpedimentoAtividade());
        user.setUsuarioId(usuarioDto(entity.getUsuarioId()));
        return user;
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

    public static Ficha toEdit(Ficha ficha,FichaCreateEditDto dto){
        if (dto == null || ficha == null)return null;

        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setPeso(dto.getPeso());
        ficha.setProblemasCardiacos(dto.getProblemasCardiacos());
        ficha.setDorPeitoAtividade(dto.getDorPeitoAtividade());
        ficha.setDorPeitoUltimoMes(dto.getDorPeitoUltimoMes());
        ficha.setProblemasCardiacos(dto.getProblemasCardiacos());
        ficha.setFuma(dto.getFuma());
        ficha.setMedicamentoPressaoCoracao(dto.getMedicamentoPressaoCoracao());
        ficha.setImpedimentoAtividade(dto.getImpedimentoAtividade());
        ficha.setProblemaOsseoArticular(ficha.getProblemaOsseoArticular());
        return ficha;
    }
}
