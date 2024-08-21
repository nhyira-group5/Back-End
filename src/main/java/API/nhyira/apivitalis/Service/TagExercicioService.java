package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Tag.TagExercicioCreateEditDto;
import API.nhyira.apivitalis.DTO.Tag.TagExercicioExibitionDto;
import API.nhyira.apivitalis.DTO.Tag.TagMapper;
import API.nhyira.apivitalis.Entity.TagExercicio;
import API.nhyira.apivitalis.Repository.TagExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagExercicioService {

    @Autowired
    private TagExercicioRepository tagExercicioRepository;

    private TagMapper tagMapper;

    public List<TagExercicioExibitionDto> getAllTagExercicios() {
        List<TagExercicio> tagExercicios = tagExercicioRepository.findAll();
        return TagMapper.toDTO(tagExercicios);
    }

    public TagExercicioExibitionDto getTagExercicioById(Integer id) {
        TagExercicio tagExercicio = tagExercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TagExercicio não encontrado com o id: " + id));
        return tagMapper.toDTO(tagExercicio);
    }

    public TagExercicioExibitionDto createTagExercicio(TagExercicioCreateEditDto tagExercicioCreateEditDTO) {
        TagExercicio tagExercicio = tagMapper.toEntity(tagExercicioCreateEditDTO);
        TagExercicio savedTagExercicio = tagExercicioRepository.save(tagExercicio);
        return tagMapper.toDTO(savedTagExercicio);
    }

    public TagExercicioExibitionDto updateTagExercicio(Integer id, TagExercicioCreateEditDto tagExercicioCreateEditDTO) {
        TagExercicio existingTagExercicio = tagExercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TagExercicio não encontrado com o id: " + id));
        existingTagExercicio.setTag(tagExercicioCreateEditDTO.getTag());
        existingTagExercicio.setExercicioId(tagExercicioCreateEditDTO.getExercicio());
        TagExercicio updatedTagExercicio = tagExercicioRepository.save(existingTagExercicio);
        return tagMapper.toDTO(updatedTagExercicio);
    }

    public void deleteTagExercicio(Integer id) {
        TagExercicio tagExercicio = tagExercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TagExercicio não encontrado com o id: " + id));
        tagExercicioRepository.delete(tagExercicio);
    }
}