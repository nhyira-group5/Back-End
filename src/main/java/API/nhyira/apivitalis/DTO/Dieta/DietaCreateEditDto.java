package API.nhyira.apivitalis.DTO.Dieta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DietaCreateEditDto {

    @NotBlank(message = "Nome da dieta obrigatorio")
    @Size(min = 10, max = 30)
    private String nome;


    @NotBlank(message = "Descrição obrigatorio")
    @Size(max = 250)
    private String descricao;

    @NotNull
    private Integer metaId;

}
