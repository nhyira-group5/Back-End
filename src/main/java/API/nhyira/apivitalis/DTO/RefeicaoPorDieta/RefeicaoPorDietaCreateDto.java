package API.nhyira.apivitalis.DTO.RefeicaoPorDieta;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefeicaoPorDietaCreateDto {
    @NotNull
    private Integer refeicaoId;

    @NotNull
    private Integer dietaId;
}
