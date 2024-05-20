package API.nhyira.apivitalis.DTO.Alimento;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class AlimentoCreateDto {
    private Integer idAlimento;

    @Size(min = 1, max = 100)
    private String nome;

    @PositiveOrZero
    private Double carboidrato;

    @PositiveOrZero
    private Double proteina;

    @PositiveOrZero
    private Double gordura;

    private Integer midiaId;
}
