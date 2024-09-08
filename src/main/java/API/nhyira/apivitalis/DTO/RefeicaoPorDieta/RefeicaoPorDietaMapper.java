package API.nhyira.apivitalis.DTO.RefeicaoPorDieta;

import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;

import java.util.List;

public class RefeicaoPorDietaMapper {
    public static RefeicaoPorDieta toEntity(RefeicaoPorDietaCreateDto dto) {
        if (dto == null) return null;
        return new RefeicaoPorDieta();
    }

    public static RefeicaoPorDietaExibitionDto toDto(RefeicaoPorDieta entity) {
        if (entity == null) return null;
        RefeicaoPorDietaExibitionDto refPorDietaDto = new RefeicaoPorDietaExibitionDto();
        refPorDietaDto.setIdRefeicaoDieta(entity.getIdRefeicaoDieta());
        refPorDietaDto.setRefeicao(toRefeicaoDto(entity.getRefeicaoId()));
        refPorDietaDto.setDieta(toDietaDto(entity.getDietaId()));
        return refPorDietaDto;
    }

    public static List<RefeicaoPorDietaExibitionDto> toDto (List<RefeicaoPorDieta> entities) {
        if (entities == null) return null;
        return entities.stream().map(RefeicaoPorDietaMapper::toDto).toList();
    }

    public static RefeicaoPorDietaExibitionDto.RefeicaoDto toRefeicaoDto(Refeicao entity) {
        if (entity == null) return null;
        RefeicaoPorDietaExibitionDto.RefeicaoDto refeicaoDto = new RefeicaoPorDietaExibitionDto.RefeicaoDto();
        refeicaoDto.setIdRefeicao(entity.getIdRefeicao());
        refeicaoDto.setNome(entity.getNome());
        refeicaoDto.setPreparo(entity.getPreparo());
        refeicaoDto.setMidia(entity.getMidiaId());
        return refeicaoDto;
    }

    public static RefeicaoPorDietaExibitionDto.DietaDto toDietaDto(Dieta entity) {
        if (entity == null) return null;
        RefeicaoPorDietaExibitionDto.DietaDto dietaDto = new RefeicaoPorDietaExibitionDto.DietaDto();
        dietaDto.setId(entity.getIdDieta());
        dietaDto.setNome(entity.getNome());
        dietaDto.setMeta(entity.getMetaId());
        dietaDto.setDescricao(entity.getDescricao());
        return dietaDto;
    }
}
