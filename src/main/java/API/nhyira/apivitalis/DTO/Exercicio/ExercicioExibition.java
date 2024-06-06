package API.nhyira.apivitalis.DTO.Exercicio;


import API.nhyira.apivitalis.Entity.Tag;
import API.nhyira.apivitalis.Entity.TagExercicio;
import lombok.Data;

import java.util.List;

@Data
public class ExercicioExibition {
    private Integer idExercicio;
    private String nome;
    private String descricao;
    private List<TagExibitionDto> tagExercicioDtos;
    private String midiaNome;     // Nome da midia
    private String midiaCaminho;  // Caminho da midia
    private String midiaExtensao;


    @Data
    public static class TagExibitionDto{

        private Integer idTagExercicio;

        private Tag tagId;


    }

}


