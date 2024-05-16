package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.Entity.Meta;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichaCreateEditDto {

    @NotNull
    private Integer bebe;
    @NotNull
    private Integer fuma;

    @NotNull
    private Integer problemasCardiacos;

    @NotNull
    private Integer dorPeitoAtividade;

    @NotNull
    private Integer dorPeitoUltimoMes;

    @NotNull
    private Integer perdaConsiencia;

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
