package API.nhyira.apivitalis.DTO.RotinaTreino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RotinaTreinoCreateEditDto {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 250)
    private String observacao;

}
