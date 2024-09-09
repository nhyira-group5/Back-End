package API.nhyira.apivitalis.DTO.Exercicio;


import API.nhyira.apivitalis.Entity.Midia;
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
    private List<MidiaExibitionDto> idMidia;

    @Data
    public static class TagExibitionDto{

        private Integer idTagExercicio;

        private Tag tagId;

    }

    @Data
    public static class MidiaExibitionDto{
        private int idMidia;
        private String nome;
        private String caminho;
        private String extensao;
        private String tipo;
    }

}


