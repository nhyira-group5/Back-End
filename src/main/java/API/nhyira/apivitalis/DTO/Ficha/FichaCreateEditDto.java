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

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String deficiencias;

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String problemasCaridiacos;

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String doencasRespiratorias;
    @DecimalMin(value = "0", inclusive = true, message = "O peso deve ser um número positivo ou zero")
    private Float peso;

    @DecimalMin(value = "0", inclusive = true, message = "O altura deve ser um número positivo ou zero")
    private float altura;

    @NotNull
    private Integer metaId;

    @NotNull
    private Integer usuarioId;

}
