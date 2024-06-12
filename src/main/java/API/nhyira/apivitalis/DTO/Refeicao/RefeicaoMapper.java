package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class RefeicaoMapper {
    public static Refeicao toEntity(RefeicaoCreateDto dto) {
        Refeicao refeicao = new Refeicao();
        refeicao.setNome(dto.getNome());
        refeicao.setPreparo(dto.getPreparo());
        return refeicao;
    }

    public static RefeicaoExibitionDto toDto (Refeicao entity) {
        RefeicaoExibitionDto refeicaoExibitionDto = new RefeicaoExibitionDto();
        refeicaoExibitionDto.setIdRefeicao(entity.getIdRefeicao());
        refeicaoExibitionDto.setNome(entity.getNome());
        refeicaoExibitionDto.setPreparo(entity.getPreparo());
        refeicaoExibitionDto.setMidia(toMidiaDto(entity.getMidiaId()));
        return refeicaoExibitionDto;
    }

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

    public static List<RefeicaoExibitionDto> toDto (List<Refeicao> entities) {
        if (entities == null) return null;
        List<Refeicao> dtos = new ArrayList<>();
        return dtos.stream().map(RefeicaoMapper::toDto).toList();
    }

    public static RefeicaoExibitionDto.MidiaDto toMidiaDto (Midia midia) {
        if (midia == null) return null;
        RefeicaoExibitionDto.MidiaDto midiaDto = new RefeicaoExibitionDto.MidiaDto();
        midiaDto.setId(midia.getIdMidia());
        midiaDto.setNome(midia.getNome());
        midiaDto.setCaminho(midia.getCaminho());
        midiaDto.setExtensao(midia.getExtensao());
        return midiaDto;
    }

    public static List<RefeicaoExibition.AlimentoPorRefeicaoDto> alimentoPorRefeicaoDto(List<AlimentoPorRefeicao> alimentoPorRefeicao){
        return alimentoPorRefeicao.stream().map(ar -> {
            RefeicaoExibition.AlimentoPorRefeicaoDto alimentoPorRefeicaoDto = new RefeicaoExibition.AlimentoPorRefeicaoDto();
            alimentoPorRefeicaoDto.setAlimento(ar.getAlimentoId());
            alimentoPorRefeicaoDto.setIdAlimentoRefeicao(ar.getIdAlimentoRefeicao());
            alimentoPorRefeicaoDto.setMetrica(ar.getMetricaId());
            alimentoPorRefeicaoDto.setQtdAlimento(ar.getQtdAlimento());
            return alimentoPorRefeicaoDto;
        }).toList();
    }



    // Controller /refeicoes/por-semana/{idRotinaSemanal}
    public static RefeicaoExibitionSemanalDto toRefeicaoExibitionSemanalDto (Refeicao ref, RotinaDiaria rd, RefeicaoDiaria refd, Midia midia) {
        if (ref == null || rd == null || refd == null) return null;

        RefeicaoExibitionSemanalDto refeicaoSemanalDto = new RefeicaoExibitionSemanalDto();
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
        rd.setId(refeicaoDiaria.getIdRotinaDiaria());
        rd.setDia(refeicaoDiaria.getDia());
        return rd;
    }

    public static RefeicaoExibitionSemanalDto.MidiaDto toSemanalMidiaDto (Midia midia) {
        if (midia == null) return null;
        RefeicaoExibitionSemanalDto.MidiaDto midiaDto = new RefeicaoExibitionSemanalDto.MidiaDto();
        midiaDto.setId(midia.getIdMidia());
        midiaDto.setNome(midia.getNome());
        midiaDto.setCaminho(midia.getCaminho());
        midiaDto.setExtensao(midia.getExtensao());
        return midiaDto;
    }
}
