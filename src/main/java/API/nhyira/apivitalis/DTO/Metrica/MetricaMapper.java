package API.nhyira.apivitalis.DTO.Metrica;

import API.nhyira.apivitalis.Entity.Metrica;

public class MetricaMapper {
    public static Metrica toEntity (MetricaCreateDto dto) {
        if (dto == null) return null;

        Metrica metrica = new Metrica();
        metrica.setNome(dto.getNome());
        return metrica;
    }

    public static MetricaExibitionDto toDto (Metrica entity) {
        if (entity == null) return null;

        MetricaExibitionDto exibitionDto = new MetricaExibitionDto();
        exibitionDto.setIdMetrica(entity.getIdMetrica());
        exibitionDto.setNome(entity.getNome());
        return exibitionDto;
    }
}
