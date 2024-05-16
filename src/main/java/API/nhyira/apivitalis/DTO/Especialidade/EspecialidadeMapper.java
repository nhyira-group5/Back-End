package API.nhyira.apivitalis.DTO.Especialidade;

import API.nhyira.apivitalis.DTO.Dieta.DietaExibitionDto;
import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Especialidade;

import java.util.List;

public class EspecialidadeMapper {


    public static EspecialidadeExibitionDto toDto(Especialidade entity){
        if (entity == null)return null;

        EspecialidadeExibitionDto exibitionDto = new EspecialidadeExibitionDto();
        exibitionDto.setId(entity.getIdEspecialidade());
        exibitionDto.setNome(entity.getNome());
        return exibitionDto;
    }

    public static List<EspecialidadeExibitionDto> toDto(List<Especialidade> entities){
        return entities.stream().map(EspecialidadeMapper::toDto).toList();

    }


}
