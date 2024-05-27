package API.nhyira.apivitalis.DTO.Metrica;

import API.nhyira.apivitalis.Entity.Metrica;

public class MetricaMapper {
    public static Metrica toEntity (MetricaCreateDto dto) {
        if (dto == null) return null;
        Metrica metrica = new Metrica();
        metrica.setMetrica(dto.getMetrica());
        return metrica;
    }

    public static MetricaExibitionDto toDto (Metrica entity) {
        if (entity == null) return null;
        MetricaExibitionDto exibitionDto = new MetricaExibitionDto();
        exibitionDto.setIdMetrica(entity.getIdMetrica());
        exibitionDto.setMetrica(entity.getMetrica());
        return exibitionDto;
    }
}
