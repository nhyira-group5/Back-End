package API.nhyira.apivitalis.DTO.RefeicaoPorDieta;

import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoPorDieta;

public class RefeicaoPorDietaMapper {
    public static RefeicaoPorDieta toEntity(RefeicaoPorDietaCreateDto dto) {
        RefeicaoPorDieta refPorDieta = new RefeicaoPorDieta();
        return refPorDieta;
    }

    public static RefeicaoPorDietaExibitionDto toDto(RefeicaoPorDieta entity) {
        RefeicaoPorDietaExibitionDto refPorDietaDto = new RefeicaoPorDietaExibitionDto();
        refPorDietaDto.setRefeicao(toRefeicaoDto(entity.getRefeicao()));
        refPorDietaDto.setDieta(toDietaDto(entity.getDieta()));

        return refPorDietaDto;
    }

    public static RefeicaoPorDietaExibitionDto.RefeicaoDto toRefeicaoDto(Refeicao entity) {
        RefeicaoPorDietaExibitionDto.RefeicaoDto refeicaoDto = new RefeicaoPorDietaExibitionDto.RefeicaoDto();
        refeicaoDto.setIdRefeicao(entity.getIdRefeicao());
        refeicaoDto.setNome(entity.getNome());
        refeicaoDto.setPreparo(entity.getPreparo());
        refeicaoDto.setMidia(entity.getMidia());

        return refeicaoDto;
    }

    public static RefeicaoPorDietaExibitionDto.DietaDto toDietaDto(Dieta entity) {
        RefeicaoPorDietaExibitionDto.DietaDto dietaDto = new RefeicaoPorDietaExibitionDto.DietaDto();
        dietaDto.setIdDieta(entity.getIdDieta());
        dietaDto.setNome(entity.getNome());
        dietaDto.setMeta(entity.getMetaId());

        return dietaDto;
    }
}
