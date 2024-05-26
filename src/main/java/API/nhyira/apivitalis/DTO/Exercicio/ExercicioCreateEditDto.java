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
    @Size(max = 255)
    private Integer midiaid;

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotNull
    @Size(max = 500)
    private String descricao;
}