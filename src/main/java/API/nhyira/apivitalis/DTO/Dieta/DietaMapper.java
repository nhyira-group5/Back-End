package API.nhyira.apivitalis.DTO.Dieta;

import API.nhyira.apivitalis.Entity.Dieta;

import java.util.List;

public class DietaMapper {

    public static Dieta toEntity(DietaCreateEditDto dto){
        if (dto == null) return null;
        Dieta dieta = new Dieta();
        dieta.setNome(dto.getNome());
        dieta.setDescricao(dto.getDescricao());
        return dieta;
    }

    public static DietaExibitionDto toDto(Dieta entity){
        if (entity == null) return null;
        DietaExibitionDto dietaExibitionDto = new DietaExibitionDto();
        dietaExibitionDto.setId(entity.getIdDieta());
        dietaExibitionDto.setNome(entity.getNome());
        dietaExibitionDto.setDescricao(entity.getDescricao());
        dietaExibitionDto.setMetaId(entity.getMetaId());
        return dietaExibitionDto;
    }

    public static List<DietaExibitionDto> toDto(List<Dieta> entities){
        return entities.stream().map(DietaMapper::toDto).toList();
    }
}
