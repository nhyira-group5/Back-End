package API.nhyira.apivitalis.DTO.Alimento;

import lombok.Data;

@Data
public class AlimentoExibitionDto {
    private Integer idAlimento;
    private String nome;
    private Double carboidrato;
    private Double proteina;
    private Double gordura;
    private MidiaDto midia;

    @Data
    public static class MidiaDto {
        private Integer idMidia;
        private String nome;
        private String caminho;
        private String extensao;
    }
}
