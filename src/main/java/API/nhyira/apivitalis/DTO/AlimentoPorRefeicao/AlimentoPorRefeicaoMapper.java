package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import API.nhyira.apivitalis.DTO.Alimento.AlimentoExibitionDto;
import API.nhyira.apivitalis.Entity.*;

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
        alimentoDto.setMidia(toMidiaDto(entity.getMidiaId()));
        return alimentoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.RefeicaoDto toRefeicaoDto(Refeicao entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto.RefeicaoDto refeicaoDto = new AlimentoPorRefeicaoExibitionDto.RefeicaoDto();
        refeicaoDto.setId(entity.getIdRefeicao());
        refeicaoDto.setNome(entity.getNome());
        refeicaoDto.setPreparo(entity.getPreparo());
        refeicaoDto.setMidia(toMidiaDto(entity.getMidiaId()));
        return refeicaoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.MetricaDto toMetricaDto(Metrica entity) {
        if (entity == null) return null;
        AlimentoPorRefeicaoExibitionDto.MetricaDto metricaDto = new AlimentoPorRefeicaoExibitionDto.MetricaDto();
        metricaDto.setId(entity.getIdMetrica());
        metricaDto.setNome(entity.getMetrica());
        return metricaDto;
    }

    public static List<AlimentoPorRefeicaoExibitionDto.MidiaDto> toMidiaDto (List<Midia> midiaList) {
        return  midiaList.stream().map(mi -> {
            AlimentoPorRefeicaoExibitionDto.MidiaDto midiaDto = new AlimentoPorRefeicaoExibitionDto.MidiaDto();
            midiaDto.setIdMidia(mi.getIdMidia());
            midiaDto.setNome(mi.getNome());
            midiaDto.setCaminho(mi.getCaminho());
            midiaDto.setExtensao(mi.getExtensao());
            midiaDto.setTipo(mi.getTipo());
            return midiaDto;
        } ).toList();
    }
}
