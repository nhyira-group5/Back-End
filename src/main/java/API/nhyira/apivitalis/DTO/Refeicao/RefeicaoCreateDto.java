package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

@Data
public class RefeicaoCreateDto {
    private String nome;
    private String preparo;
    private Integer midiaId;
}
