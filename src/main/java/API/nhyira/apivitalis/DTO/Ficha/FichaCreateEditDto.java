package API.nhyira.apivitalis.DTO.Ficha;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FichaCreateEditDto {

    @NotNull
    private Integer problemaCardiaco;

    @NotNull
    private Integer dorPeitoAtividade;

    @NotNull
    private Integer dorPeitoUltimoMes;

    @NotNull
    private Integer problemaOsseoArticular;

    @NotNull
    private Integer medicamentoPressaoCoracao;

    @NotNull
    private Integer impedimentoAtividade;

    @DecimalMin(value = "0.00", inclusive = true, message = "O altura deve ser um número positivo ou zero")
    private float altura;

    @DecimalMin(value = "0.00")
    private float peso;

    @NotNull
    private int usuarioId;
}
