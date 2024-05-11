package API.nhyira.apivitalis.DTO.RotinaTreino;

import API.nhyira.apivitalis.Entity.RotinaTreino;

import java.util.List;

public class RotinaTreinoMapper {

    public static RotinaTreino toEntity(RotinaTreinoCreateEditDto dto){
        if (dto == null)return null;

        RotinaTreino rotinaTreino = new RotinaTreino();
        rotinaTreino.setNome(dto.getNome());
        rotinaTreino.setObservacoes(dto.getObservacao());
        return rotinaTreino;
    }

    public static RotinaTreinoExibitionDto toDto(RotinaTreino entity){
        if (entity == null)return null;

        RotinaTreinoExibitionDto user = new RotinaTreinoExibitionDto();
        user.setIdRotinaTreino(entity.getIdRotinaTreino());
        user.setNome(entity.getNome());
        user.setObservacao(entity.getObservacoes());
        return user;
    }
    public static List<RotinaTreinoExibitionDto> toDto(List<RotinaTreino> entities){
        return entities.stream().map(RotinaTreinoMapper::toDto).toList();
    }


    public static RotinaTreino toEdit(RotinaTreino entity ,RotinaTreinoCreateEditDto dto){
        if (dto == null || entity == null)return null;

        entity.setNome(dto.getNome());
        entity.setObservacoes(dto.getObservacao());
        return entity;
    }







}
