package API.nhyira.apivitalis.DTO.Exercicio;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagExercicioDto {

    @NotNull
    private Integer idTagExercicio;

    @NotNull
    private Integer tagId;

    @NotNull
    private Integer exercicioId;
}