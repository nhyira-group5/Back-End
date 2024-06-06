package API.nhyira.apivitalis.DTO.Exercicio;

import API.nhyira.apivitalis.Entity.TagExercicio;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagExercicioDto {

    @NotNull
    private TagExercicio idTagExercicio;

    @NotNull
    private Integer tagId;

    @NotNull
    private Integer exercicioId;
}