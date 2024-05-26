package API.nhyira.apivitalis.DTO.Tag;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibitionDto;
import API.nhyira.apivitalis.Entity.Tag;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagExercicioExibitionDto {

    private Integer idTagExercicio;

    private Tag tag;

    private ExercicioExibitionDto exercicio;
}