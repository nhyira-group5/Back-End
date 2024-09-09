package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.Entity.*;

import java.util.List;

public class RefeicaoMapper {
    public static Refeicao toEntity(RefeicaoCreateDto dto) {
        Refeicao refeicao = new Refeicao();
        refeicao.setNome(dto.getNome());
        refeicao.setPreparo(dto.getPreparo());
        return refeicao;
    }

//    public static RefeicaoExibitionDto toDto (Refeicao entity) {
//        RefeicaoExibitionDto refeicaoExibitionDto = new RefeicaoExibitionDto();
//        refeicaoExibitionDto.setIdRefeicao(entity.getIdRefeicao());
//        refeicaoExibitionDto.setNome(entity.getNome());
//        refeicaoExibitionDto.setPreparo(entity.getPreparo());
//        refeicaoExibitionDto.setMidia(toMidiaDto(entity.getMidiaId()));
//        return refeicaoExibitionDto;
//    }

    public static RefeicaoExibition toDTO (Refeicao entity) {
        RefeicaoExibition refeicaoExibition = new RefeicaoExibition();
        refeicaoExibition.setIdRefeicao(entity.getIdRefeicao());
        refeicaoExibition.setNome(entity.getNome());
        refeicaoExibition.setPreparo(entity.getPreparo());
        refeicaoExibition.setMidia(toMidiaDto(entity.getMidiaId()));
        refeicaoExibition.setAlimentoPorRefeicao(alimentoPorRefeicaoDto(entity.getAlimentoPorRefeicaos()));
        return refeicaoExibition;
    }

    public static List<RefeicaoExibition> toDTO (List<Refeicao> entity){
        return entity.stream().map(RefeicaoMapper::toDTO).toList();
    }

    public static List<RefeicaoExibition.MidiaDto> toMidiaDto (List<Midia> midias) {
        return midias.stream().map(mi ->{
            RefeicaoExibition.MidiaDto midiaDto = new RefeicaoExibition.MidiaDto();
            midiaDto.setIdMidia(mi.getIdMidia());
            midiaDto.setNome(mi.getNome());
            midiaDto.setTipo(mi.getTipo());
            midiaDto.setExtensao(mi.getExtensao());
            midiaDto.setCaminho(mi.getCaminho());
            return midiaDto;
        }).toList();
    }

    public static List<RefeicaoExibition.AlimentoPorRefeicaoDto> alimentoPorRefeicaoDto(List<AlimentoPorRefeicao> alimentoPorRefeicao){
        return alimentoPorRefeicao.stream().map(ar -> {
            RefeicaoExibition.AlimentoPorRefeicaoDto alimentoPorRefeicaoDto = new RefeicaoExibition.AlimentoPorRefeicaoDto();
            alimentoPorRefeicaoDto.setAlimento(alimentoDto(ar.getAlimentoId()));
            alimentoPorRefeicaoDto.setIdAlimentoRefeicao(ar.getIdAlimentoRefeicao());
            alimentoPorRefeicaoDto.setMetrica(ar.getMetricaId());
            alimentoPorRefeicaoDto.setQtdAlimento(ar.getQtdAlimento());
            return alimentoPorRefeicaoDto;
        }).toList();
    }

    public static RefeicaoExibition.AlimentoDto alimentoDto(Alimento alimentos){

            RefeicaoExibition.AlimentoDto alimentoDto = new RefeicaoExibition.AlimentoDto();
            alimentoDto.setId(alimentos.getIdAlimento());
            alimentoDto.setGordura(alimentos.getGordura());
            alimentoDto.setCarboidrato(alimentos.getCarboidrato());
            alimentoDto.setProteina(alimentos.getProteina());
            alimentoDto.setNome(alimentos.getNome());
            alimentoDto.setMidia(toMidiaDto(alimentos.getMidiaId()));
            return alimentoDto;
    }

    // Controller /refeicoes/por-semana/{idRotinaSemanal}
    public static RefeicaoExibitionSemanalDto toRefeicaoExibitionSemanalDto (Refeicao ref, RotinaDiaria rd, RefeicaoDiaria refd, List<Midia> midia, int idRefeicaoDiaria) {
        if (ref == null || rd == null || refd == null) return null;

        RefeicaoExibitionSemanalDto refeicaoSemanalDto = new RefeicaoExibitionSemanalDto();
        refeicaoSemanalDto.setIdRefeicaoDiaria(idRefeicaoDiaria);
        refeicaoSemanalDto.setIdRefeicao(ref.getIdRefeicao());
        refeicaoSemanalDto.setNome(ref.getNome());
        refeicaoSemanalDto.setPreparo(ref.getPreparo());
        refeicaoSemanalDto.setConcluido(refd.getConcluido());
        refeicaoSemanalDto.setRotinaDiaria(toRotinaDiaria(rd));
        refeicaoSemanalDto.setMidia(toSemanalMidiaDto(midia));
        return refeicaoSemanalDto;
    }

    public static RefeicaoExibitionSemanalDto.RotinaDiariaDto toRotinaDiaria (RotinaDiaria refeicaoDiaria) {
        if (refeicaoDiaria == null) return null;
        RefeicaoExibitionSemanalDto.RotinaDiariaDto rd = new RefeicaoExibitionSemanalDto.RotinaDiariaDto();
        rd.setIdRotinaDiaria(refeicaoDiaria.getIdRotinaDiaria());
        rd.setDia(refeicaoDiaria.getDia());
        return rd;
    }

    public static List<RefeicaoExibitionSemanalDto.MidiaDto> toSemanalMidiaDto (List<Midia> midias) {
        return midias.stream().map(mi ->{
            RefeicaoExibitionSemanalDto.MidiaDto midiaDto = new RefeicaoExibitionSemanalDto.MidiaDto();
            midiaDto.setId(mi.getIdMidia());
            midiaDto.setNome(mi.getNome());
            midiaDto.setTipo(mi.getTipo());
            midiaDto.setExtensao(mi.getExtensao());
            midiaDto.setCaminho(mi.getCaminho());
            return midiaDto;
        }).toList();
    }
}
