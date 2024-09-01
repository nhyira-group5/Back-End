package API.nhyira.apivitalis.DTO.Exercicio;


import API.nhyira.apivitalis.Entity.Midia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioExibitionDto {

    private Integer idExercicio;
    private String nome;
    private String descricao;
    private List<Midia> midiaList;

}
