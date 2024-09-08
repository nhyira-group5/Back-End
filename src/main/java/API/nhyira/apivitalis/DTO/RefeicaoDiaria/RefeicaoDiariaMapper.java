package API.nhyira.apivitalis.DTO.RefeicaoDiaria;

import API.nhyira.apivitalis.DTO.RefeicaoPorDieta.RefeicaoPorDietaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaDiaria.RotinaDiariaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;

public class RefeicaoDiariaMapper {
    public static RefeicaoDiariaExibitionDto toDto(RefeicaoDiaria refeicaoDiaria) {
        if (refeicaoDiaria == null) return null;

        RefeicaoDiariaExibitionDto refeicaoDiariaExibitionDto = new RefeicaoDiariaExibitionDto();
        refeicaoDiariaExibitionDto.setConcluido(refeicaoDiaria.getConcluido());
        refeicaoDiariaExibitionDto.setIdRefeicaoDiaria(refeicaoDiaria.getIdRefeicaoDiaria());
        refeicaoDiariaExibitionDto.setRotinaDiariaId(rotinaDiariaDto(refeicaoDiaria.getRotinaDiariaId()));
        refeicaoDiariaExibitionDto.setRefeicaoId(refeicaoDto(refeicaoDiaria.getRefeicaoId()));
        return refeicaoDiariaExibitionDto;
    }

    public static RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto(RotinaDiaria rotinaDiaria) {
        if (rotinaDiaria == null) return null;

        RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto = new RotinaSemanalExibitionDto.RotinaDiariaDto();
        rotinaDiariaDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaDto.setConcluido(rotinaDiaria.getConcluido());
        return rotinaDiariaDto;
    }

    public static RefeicaoPorDietaExibitionDto.RefeicaoDto refeicaoDto(Refeicao refeicao) {
        if (refeicao == null) return null;

        RefeicaoPorDietaExibitionDto.RefeicaoDto refeicaoDto = new RefeicaoPorDietaExibitionDto.RefeicaoDto();
        refeicaoDto.setIdRefeicao(refeicao.getIdRefeicao());
        refeicaoDto.setNome(refeicao.getNome());
        refeicaoDto.setPreparo(refeicao.getPreparo());
        return refeicaoDto;
    }
}
