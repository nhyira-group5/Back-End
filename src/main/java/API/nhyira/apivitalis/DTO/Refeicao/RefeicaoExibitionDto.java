package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RefeicaoExibitionDto {
    private Integer idRefeicao;
    private String nome;
    private String preparo;
    private MidiaDto midia;

    @Data
    public static class MidiaDto {
        private Integer id;
        private String nome;
        private String caminho;
        private String extensao;
    }
}
