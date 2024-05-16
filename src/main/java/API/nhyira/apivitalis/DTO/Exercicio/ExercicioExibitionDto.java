package API.nhyira.apivitalis.DTO.Exercicio;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioExibitionDto {
    private Integer idTreino;
    private String midiaUrl;
    private String nome;
    private String descricao;

}
