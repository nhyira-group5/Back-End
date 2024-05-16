package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Tag.TagExibitionDto;
import API.nhyira.apivitalis.Entity.Tag;
import API.nhyira.apivitalis.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagExibitionDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TagExibitionDto getTagById(Integer id) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        return toDTO(tag);
    }

    private TagExibitionDto toDTO(Tag tag) {
        return new TagExibitionDto(
                tag.getIdTag(),
                tag.getNome()
        );
    }
}