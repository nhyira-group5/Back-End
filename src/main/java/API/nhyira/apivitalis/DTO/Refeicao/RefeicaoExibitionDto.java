package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RefeicaoExibitionDto {
    private Integer idRefeicao;
    private String nome;
    private String preparo;
    private List<MidiaDto> midia;

    @Data
    public static class MidiaDto {
        private Integer id;
        private String nome;
        private String caminho;
        private String extensao;
        private String tipo;
    }
}
