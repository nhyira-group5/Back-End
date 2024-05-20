package API.nhyira.apivitalis.DTO.RefeicaoPorDieta;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Midia;
import lombok.Data;

@Data
public class RefeicaoPorDietaExibitionDto {
    private Integer idRefeicaoPorDieta;
    private DietaDto dieta;
    private RefeicaoDto refeicao;

    @Data
    public static class RefeicaoDto {
        private Integer idRefeicao;
        private String nome;
        private String preparo;
        private Midia midia;
    }

    @Data
    public static class DietaDto {
        private Integer idDieta;
        private String nome;
        private String descricao;
        private Meta meta;
    }
}
