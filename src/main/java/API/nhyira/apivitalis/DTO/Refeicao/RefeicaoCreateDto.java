package API.nhyira.apivitalis.DTO.Refeicao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RefeicaoCreateDto {

    @NotBlank
    private String nome;

    @Size(min = 1, max = 5001)
    private String preparo;

    private Integer midiaId;
}
