package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Tag.TagExercicioExibitionDto;
import API.nhyira.apivitalis.Entity.TagExercicio;
import API.nhyira.apivitalis.Repository.TagExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagExercicioService {

//    @Autowired
//    private TagExercicioRepository tagExercicioRepository;
//
//    public List<TagExercicioExibitionDto> getAllTagExercicios() {
//        return tagExercicioRepository.findAll().stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public TagExercicioExibitionDto getTagExercicioById(Integer id) {
//        TagExercicio tagExercicio = tagExercicioRepository.findById(id).orElseThrow();
//        return toDTO(tagExercicio);
//    }
//
//    private TagExercicioExibitionDto toDTO(TagExercicio tagExercicio) {
//        return new TagExercicioExibitionDto(
//                tagExercicio.getIdTagExercicio(),
//                tagExercicio.getTag()
//        );
//    }
}