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

    private Integer idExercicio;
    private String nome;
    private String descricao;
    private String midiaNome;     // Nome da midia
    private String midiaCaminho;  // Caminho da midia
    private String midiaExtensao; // Extensão da midia

}
