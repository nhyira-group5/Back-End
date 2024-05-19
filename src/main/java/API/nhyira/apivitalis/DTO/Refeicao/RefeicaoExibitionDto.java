package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RefeicaoExibitionDto {
    private Integer idRefeicao;

    @Size(min = 1, max = 100)
    private String nome;

    @Size(min = 1, max = 550)
    private String preparo;

    private MidiaDto midia;

    @Data
    public static class MidiaDto {
        private Integer idMidia;
        private String nome;
        private String caminho;
        private String extensao;
    }
}
