package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.Entity.Ficha;

public class FichaMapper {

    public static Ficha toDto(FichaCreateEditDto dto){
        if (dto == null)return null;

        Ficha ficha = new Ficha();
        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setMeta(dto.getMeta());
        ficha.setPeso(dto.getPeso());
        ficha.setDeficiencias(dto.getDeficiencias());
        ficha.setFuma(dto.getFuma());
        ficha.setDoencasRespiratorias(dto.getDoencasRespiratorias());
        ficha.setProblemasCaridiacos(dto.getProblemasCaridiacos());
        return ficha;
    }

    public static FichaExibitionDto toEntity(Ficha entity){
        if (entity == null)return null;

        FichaExibitionDto user = new FichaExibitionDto();
        user.setAltura(entity.getAltura());
        user.setBebe(entity.getBebe());
        user.setMeta(entity.getMeta());
        user.setDeficiencias(entity.getDeficiencias());
        user.setPeso(entity.getPeso());
        user.setFuma(entity.getFuma());
        user.setDoencasRespiratorias(entity.getDoencasRespiratorias());
        user.setProblemasCaridiacos(entity.getProblemasCaridiacos());
        user.setUsuarioId(entity.getFkUsuario());
        return user;
    }

    public static Ficha toEdit(Ficha ficha,FichaCreateEditDto dto){
        if (dto == null || ficha == null)return null;

        ficha.setAltura(dto.getAltura());
        ficha.setBebe(dto.getBebe());
        ficha.setMeta(dto.getMeta());
        ficha.setPeso(dto.getPeso());
        ficha.setDeficiencias(dto.getDeficiencias());
        ficha.setFuma(dto.getFuma());
        ficha.setDoencasRespiratorias(dto.getDoencasRespiratorias());
        ficha.setProblemasCaridiacos(dto.getProblemasCaridiacos());
        return ficha;
    }
}
