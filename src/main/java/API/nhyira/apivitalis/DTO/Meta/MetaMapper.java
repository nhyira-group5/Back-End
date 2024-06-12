package API.nhyira.apivitalis.DTO.Meta;

import API.nhyira.apivitalis.DTO.Meta.MetaExibitionDto;
import API.nhyira.apivitalis.Entity.Meta;

import java.util.List;

public class MetaMapper {

    public static MetaExibitionDto toDto(Meta meta){
        if (meta == null)return null;

        MetaExibitionDto metaExibitionDto = new MetaExibitionDto();

        metaExibitionDto.setId(meta.getIdMeta());
        metaExibitionDto.setNome(meta.getNome());
        return metaExibitionDto;
    }

    public static List<MetaExibitionDto> toDto(List<Meta> metas){
        return metas.stream().map(MetaMapper::toDto).toList();
    }

}
