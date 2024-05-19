package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Metrica;
import API.nhyira.apivitalis.Entity.Refeicao;

public class AlimentoPorRefeicaoMapper {
    public static AlimentoPorRefeicao toEntity(AlimentoPorRefeicaoCreateDto dto) {
        AlimentoPorRefeicao aliPorRef = new AlimentoPorRefeicao();
        aliPorRef.setQtdAlimento(dto.getQtdAlimentoPorRefeicao());

        return aliPorRef;
    }

    public static AlimentoPorRefeicaoExibitionDto toDto(AlimentoPorRefeicao entity) {
        AlimentoPorRefeicaoExibitionDto aliPorRef = new AlimentoPorRefeicaoExibitionDto();
        aliPorRef.setQtdAlimento(entity.getQtdAlimento());
        aliPorRef.setAlimento(toAlimentoDto(entity.getAlimento()));
        aliPorRef.setRefeicao(toRefeicaoDto(entity.getRefeicao()));
        aliPorRef.setMetrica(toMetricaDto(entity.getMetrica()));

        return aliPorRef;
    }

    public static AlimentoPorRefeicaoExibitionDto.AlimentoDto toAlimentoDto(Alimento entity) {
        AlimentoPorRefeicaoExibitionDto.AlimentoDto alimentoDto = new AlimentoPorRefeicaoExibitionDto.AlimentoDto();
        alimentoDto.setIdAlimento(entity.getIdAlimento());
        alimentoDto.setNome(entity.getNome());
        alimentoDto.setCarboidrato(entity.getCarboidrato());
        alimentoDto.setProteina(entity.getProteina());
        alimentoDto.setGordura(entity.getGordura());
        alimentoDto.setMidia(entity.getMidia());

        return alimentoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.RefeicaoDto toRefeicaoDto(Refeicao entity) {
        AlimentoPorRefeicaoExibitionDto.RefeicaoDto refeicaoDto = new AlimentoPorRefeicaoExibitionDto.RefeicaoDto();
        refeicaoDto.setIdRefeicao(entity.getIdRefeicao());
        refeicaoDto.setNome(entity.getNome());
        refeicaoDto.setPreparo(entity.getPreparo());
        refeicaoDto.setMidia(entity.getMidia());

        return refeicaoDto;
    }

    public static AlimentoPorRefeicaoExibitionDto.MetricaDto toMetricaDto(Metrica entity) {
        AlimentoPorRefeicaoExibitionDto.MetricaDto metricaDto = new AlimentoPorRefeicaoExibitionDto.MetricaDto();
        metricaDto.setIdMetrica(entity.getIdMetrica());
        metricaDto.setNome(entity.getNome());

        return metricaDto;
    }
}
