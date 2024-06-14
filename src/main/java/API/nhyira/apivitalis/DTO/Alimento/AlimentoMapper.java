package API.nhyira.apivitalis.DTO.Alimento;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.Midia;

import java.util.ArrayList;
import java.util.List;

public class AlimentoMapper {
    public static Alimento toEntity (AlimentoCreateDto dto) {
        if (dto == null) return null;
        Alimento alimento = new Alimento();
        alimento.setIdAlimento(dto.getIdAlimento());
        alimento.setNome(dto.getNome());
        alimento.setCarboidrato(dto.getCarboidrato());
        alimento.setProteina(dto.getProteina());
        alimento.setGordura(dto.getGordura());
        return alimento;
    }

    public static AlimentoExibitionDto toDto (Alimento entity) {
        if (entity == null) return null;
        AlimentoExibitionDto dto = new AlimentoExibitionDto();
        dto.setIdAlimento(entity.getIdAlimento());
        dto.setNome(entity.getNome());
        dto.setCarboidrato(entity.getCarboidrato());
        dto.setProteina(entity.getProteina());
        dto.setGordura(entity.getGordura());
        dto.setMidia(toMidiaDto(entity.getMidiaId()));
        return dto;
    }

    public static List<AlimentoExibitionDto> toDto (List<Alimento> entities) {
        if (entities == null) return null;
        return entities.stream().map(AlimentoMapper::toDto).toList();
    }

    public static AlimentoExibitionDto.MidiaDto toMidiaDto (Midia midia) {
        if (midia == null) return null;
        AlimentoExibitionDto.MidiaDto midiaDto = new AlimentoExibitionDto.MidiaDto();
        midiaDto.setIdMidia(midia.getIdMidia());
        midiaDto.setNome(midia.getNome());
        midiaDto.setCaminho(midia.getCaminho());
        midiaDto.setExtensao(midia.getExtensao());
        return midiaDto;
    }
}
