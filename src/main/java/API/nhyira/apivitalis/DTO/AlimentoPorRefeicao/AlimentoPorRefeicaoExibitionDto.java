package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import API.nhyira.apivitalis.Entity.Midia;
import lombok.Data;

@Data
public class AlimentoPorRefeicaoExibitionDto {
    private Integer idAlimentoPorRefeicao;
    private int qtdAlimento;
    private AlimentoDto alimento;
    private RefeicaoDto refeicao;
    private MetricaDto metrica;

    @Data
    public static class AlimentoDto {
        private Integer idAlimento;
        private String nome;
        private Double carboidrato;
        private Double proteina;
        private Double gordura;
        private Midia midia;
    }

    @Data
    public static class RefeicaoDto {
        private Integer idRefeicao;
        private String nome;
        private String preparo;
        private Midia midia;
    }

    @Data
    public static class MetricaDto {
        private Integer idMetrica;
        private String nome;
    }
}
