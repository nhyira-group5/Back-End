package API.nhyira.apivitalis.DTO.EspecialidadePorMeta;

import API.nhyira.apivitalis.Entity.EspecialidadePorMeta;

import java.util.List;

public class EspecialidadePorMetaMapper {


    public static EspecialidadePorMetaExibitionDto toDto(EspecialidadePorMeta especialidadePorMeta){
        if (especialidadePorMeta == null)return null;
        EspecialidadePorMetaExibitionDto exibitionDto = new EspecialidadePorMetaExibitionDto();
        exibitionDto.setIdEspecialidadeMeta(especialidadePorMeta.getIdEspecialidadeMeta());
        exibitionDto.setEspecialidadeId(especialidadePorMeta.getEspecialidadeId());
        exibitionDto.setMetaId(especialidadePorMeta.getMetaId());
        exibitionDto.setEspecialidadeId(especialidadePorMeta.getEspecialidadeId());
        return exibitionDto;
    }

    public static List<EspecialidadePorMetaExibitionDto> toDto(List<EspecialidadePorMeta> especialidadePorMetas){
        return especialidadePorMetas.stream().map(EspecialidadePorMetaMapper::toDto).toList();
    }


}
