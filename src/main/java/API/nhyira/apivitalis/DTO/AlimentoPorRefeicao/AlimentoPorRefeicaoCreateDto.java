package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AlimentoPorRefeicaoCreateDto {
    @Size(min = 1)
    private int qtdAlimentoPorRefeicao;

    @NotNull
    private Integer alimentoId;

    @NotNull
    private Integer refeicaoId;

    @NotNull
    private Integer metricaId;
}
