package API.nhyira.apivitalis.DTO.Tag;


import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagExercicioCreateEditDto {
    @NotNull
    private Tag tag;

    @NotNull
    private Exercicio exercicio;


}
