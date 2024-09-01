package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import API.nhyira.apivitalis.Entity.Midia;
import lombok.Data;

import java.util.List;

@Data
public class AlimentoPorRefeicaoExibitionDto {
    private Integer idAlimentoRefeicao;
    private int qtdAlimento;
    private AlimentoDto alimento;
    private RefeicaoDto refeicao;
    private MetricaDto metrica;

    @Data
    public static class AlimentoDto {
        private Integer id;
        private String nome;
        private Double carboidrato;
        private Double proteina;
        private Double gordura;
        private List<MidiaDto> midia;
    }

    @Data
    public static class RefeicaoDto {
        private Integer id;
        private String nome;
        private String preparo;
        private List<MidiaDto> midia;
    }

    @Data
    public static class MetricaDto {
        private Integer id;
        private String nome;
    }

    @Data
    public static class MidiaDto {
        private Integer idMidia;
        private String nome;
        private String caminho;
        private String extensao;
        private String tipo;
    }
}
