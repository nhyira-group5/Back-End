package API.nhyira.apivitalis.DTO.RotinaUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RotinaTreinoCreateEditDto {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 250)
    private String observacao;
}
