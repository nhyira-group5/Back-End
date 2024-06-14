package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.Metrica;
import lombok.Data;

import java.util.List;

@Data
public class RefeicaoExibition {
    private Integer idRefeicao;
    private String nome;
    private String preparo;

    private RefeicaoExibitionDto.MidiaDto midia;
    private List<AlimentoPorRefeicaoDto> alimentoPorRefeicao;

    @Data
    public static class AlimentoPorRefeicaoDto{
        private Integer idAlimentoRefeicao;
        private int qtdAlimento;
        private Alimento alimento;
        private Metrica metrica;
    }
}
