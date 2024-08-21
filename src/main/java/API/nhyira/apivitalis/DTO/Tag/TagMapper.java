package API.nhyira.apivitalis.DTO.Tag;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibitionDto;
import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.Tag;
import API.nhyira.apivitalis.Entity.TagExercicio;

import java.util.List;

public class TagMapper {
    public TagExibitionDto toDTO(Tag tag) {
        TagExibitionDto dto = new TagExibitionDto();
        dto.setIdTag(tag.getIdTag());
        dto.setNome(tag.getNome());
        return dto;
    }

    public static Tag toEntity(TagCreateEditDto tagDTO) {
        Tag tag = new Tag();
        tag.setNome(tagDTO.getNome());
        return tag;
    }

    public static TagExercicioExibitionDto toDTO(TagExercicio tagExercicio) {
        TagExercicioExibitionDto dto = new TagExercicioExibitionDto();
        dto.setIdTagExercicio(tagExercicio.getIdTagExercicio());
        dto.setTag(tagExercicio.getTag());
        dto.setExercicio(exibitionDto(tagExercicio.getExercicioId()));
        return dto;
    }

    public static List<TagExercicioExibitionDto> toDTO(List<TagExercicio> tagExercicios){
        return tagExercicios.stream().map(TagMapper::toDTO).toList();
    }


    public static TagExibitionDto toDTOTags(Tag tag){
        if (tag == null)return null;
        return TagExibitionDto.builder().idTag(tag.getIdTag()).nome(tag.getNome()).build();
    }

    public static List<TagExibitionDto> toDTOTags(List<Tag> tags){
        return tags.stream().map(TagMapper::toDTOTags).toList();
    }

    public static ExercicioExibitionDto exibitionDto(Exercicio exercicio){
        if (exercicio == null)return null;
        ExercicioExibitionDto exibitionDto = new ExercicioExibitionDto();
        exibitionDto.setIdExercicio(exercicio.getIdExercicio());
        exibitionDto.setNome(exercicio.getNome());
        exibitionDto.setDescricao(exercicio.getDescricao());
        return exibitionDto;

    }

    public static TagExercicio toEntity(TagExercicioCreateEditDto tagExercicioDTO) {
        TagExercicio tagExercicio = new TagExercicio();
        tagExercicio.setTag(tagExercicioDTO.getTag());
        tagExercicio.setExercicioId(tagExercicioDTO.getExercicio());
        return tagExercicio;
    }

}
