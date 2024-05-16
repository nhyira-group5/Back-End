package API.nhyira.apivitalis.DTO.Exercicio;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  ExercicioCreateEditDto {

    @NotNull
    private Integer idTreino;

    @NotNull
    @Size(max = 255)
    private String midiaUrl;

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotNull
    @Size(max = 500)
    private String descricao;
}