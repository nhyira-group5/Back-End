package API.nhyira.apivitalis.DTO.Meta;

import API.nhyira.apivitalis.DTO.Meta.MetaExibitionDto;
import API.nhyira.apivitalis.Entity.Meta;

public class MetaMapper {

    public static MetaExibitionDto toDto(Meta meta){
        if (meta == null)return null;

        MetaExibitionDto metaExibitionDto = new MetaExibitionDto();

        metaExibitionDto.setId(meta.getIdMeta());
        metaExibitionDto.setNome(meta.getNome());
        return metaExibitionDto;
    }

}
