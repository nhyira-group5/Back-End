package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Metrica;
import API.nhyira.apivitalis.Entity.Refeicao;

import java.util.ArrayList;
import java.util.List;

public class AlimentoPorRefeicaoMapper {
    public static AlimentoPorRefeicao toEntity(AlimentoPorRefeicaoCreateDto dto) {
        if (dto == null) return null;
        AlimentoPorRefeicao aliPorRef = new AlimentoPorRefeicao();
        aliPorRef.setQtdAlimento(dto.getQtdAlimentoPorRefeicao());
        return aliPorRef;
    }

    public static AlimentoPorRefeicaoExibitionDto toDto(AlimentoPorRefeicao entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto aliPorRef = new AlimentoPorRefeicaoExibitionDto();
        aliPorRef.setIdAlimentoRefeicao(entity.getIdAlimentoRefeicao());
        aliPorRef.setQtdAlimento(entity.getQtdAlimento());
        aliPorRef.setAlimento(toAlimentoDto(entity.getAlimentoId()));
        aliPorRef.setRefeicao(toRefeicaoDto(entity.getRefeicaoId()));
        aliPorRef.setMetrica(toMetricaDto(entity.getMetricaId()));
        return aliPorRef;
    }

    public static List<AlimentoPorRefeicaoExibitionDto> toDto (List<AlimentoPorRefeicao> entities) {
        if (entities == null) return null;
        return entities.stream().map(AlimentoPorRefeicaoMapper::toDto).toList();
    }

    public static AlimentoPorRefeicaoExibitionDto.AlimentoDto toAlimentoDto(Alimento entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto.AlimentoDto alimentoDto = new AlimentoPorRefeicaoExibitionDto.AlimentoDto();
        alimentoDto.setId(entity.getIdAlimento());
        alimentoDto.setNome(entity.getNome());
        alimentoDto.setCarboidrato(entity.getCarboidrato());
        alimentoDto.setProteina(entity.getProteina());
        alimentoDto.setGordura(entity.getGordura());
        alimentoDto.setMidia(entity.getMidiaId());
        return alimentoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.RefeicaoDto toRefeicaoDto(Refeicao entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto.RefeicaoDto refeicaoDto = new AlimentoPorRefeicaoExibitionDto.RefeicaoDto();
        refeicaoDto.setId(entity.getIdRefeicao());
        refeicaoDto.setNome(entity.getNome());
        refeicaoDto.setPreparo(entity.getPreparo());
        refeicaoDto.setMidia(entity.getMidiaId());
        return refeicaoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.MetricaDto toMetricaDto(Metrica entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto.MetricaDto metricaDto = new AlimentoPorRefeicaoExibitionDto.MetricaDto();
        metricaDto.setId(entity.getIdMetrica());
        metricaDto.setNome(entity.getMetrica());
        return metricaDto;
    }
}
