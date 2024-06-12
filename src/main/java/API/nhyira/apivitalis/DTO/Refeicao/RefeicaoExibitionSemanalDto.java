package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

@Data
public class RefeicaoExibitionSemanalDto {
    private Integer idRefeicao;
    private String nome;
    private String preparo;
    private Integer concluido;
    private RotinaDiariaDto rotinaDiaria;
    private MidiaDto midia;

    @Data
    public static class MidiaDto {
        private Integer id;
        private String nome;
        private String caminho;
        private String extensao;
    }

    @Data
    public static class RotinaDiariaDto {
        private Integer id;
        private Integer dia;
    }
}
