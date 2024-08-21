package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Tag.TagCreateEditDto;
import API.nhyira.apivitalis.DTO.Tag.TagExibitionDto;
import API.nhyira.apivitalis.Entity.Tag;
import API.nhyira.apivitalis.DTO.Tag.TagMapper;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    private TagMapper tagMapper;

    public List<TagExibitionDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return TagMapper.toDTOTags(tags);
    }

    public TagExibitionDto getTagById(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o id: " + id));
        return TagMapper.toDTOTags(tag);
    }

    public TagExibitionDto createTag(TagCreateEditDto tagCreateEditDTO) {
        Tag tag = tagMapper.toEntity(tagCreateEditDTO);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toDTO(savedTag);
    }

    public TagExibitionDto updateTag(Integer id, TagCreateEditDto tagCreateEditDTO) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o id: " + id));
        existingTag.setNome(tagCreateEditDTO.getNome());
        Tag updatedTag = tagRepository.save(existingTag);
        return tagMapper.toDTO(updatedTag);
    }

    public void deleteTag(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o id: " + id));
        tagRepository.delete(tag);
    }
}