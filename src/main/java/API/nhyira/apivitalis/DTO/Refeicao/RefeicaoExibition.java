package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.Metrica;
import API.nhyira.apivitalis.Entity.Midia;
import lombok.Data;

import java.util.List;

@Data
public class RefeicaoExibition {
    private Integer idRefeicao;
    private String nome;
    private String preparo;
    private List<MidiaDto> midia;
    private List<AlimentoPorRefeicaoDto> alimentoPorRefeicao;

    @Data
    public static class AlimentoPorRefeicaoDto{
        private Integer idAlimentoRefeicao;
        private int qtdAlimento;
        private AlimentoDto alimento;
        private Metrica metrica;
    }

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
    public static class MidiaDto {
        private Integer idMidia;
        private String nome;
        private String caminho;
        private String extensao;
        private String tipo;
    }
}
