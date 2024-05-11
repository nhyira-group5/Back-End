package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.Entity.Ficha;

import java.util.List;
import java.util.stream.Collectors;

public class FichaMapper {

    public static Ficha toEntity(FichaCreateEditDto dto){
        if (dto == null)return null;

        Ficha ficha = new Ficha();
        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setPeso(dto.getPeso());
        ficha.setDeficiencias(dto.getDeficiencias());
        ficha.setFuma(dto.getFuma());
        ficha.setDoencasRespiratorias(dto.getDoencasRespiratorias());
        ficha.setProblemasCaridiacos(dto.getProblemasCaridiacos());
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
        user.setMetaId(entity.getMetaId());
        user.setDeficiencias(entity.getDeficiencias());
        user.setPeso(entity.getPeso());
        user.setFuma(entity.getFuma());
        user.setDoencasRespiratorias(entity.getDoencasRespiratorias());
        user.setProblemasCaridiacos(entity.getProblemasCaridiacos());
        user.setUsuarioId(entity.getUsuarioId());
        return user;
    }

    public static Ficha toEdit(Ficha ficha,FichaCreateEditDto dto){
        if (dto == null || ficha == null)return null;

        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setPeso(dto.getPeso());
        ficha.setDeficiencias(dto.getDeficiencias());
        ficha.setFuma(dto.getFuma());
        ficha.setDoencasRespiratorias(dto.getDoencasRespiratorias());
        ficha.setProblemasCaridiacos(dto.getProblemasCaridiacos());
        return ficha;
    }
}
