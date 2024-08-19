package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Tag.TagCreateEditDto;
import API.nhyira.apivitalis.DTO.Tag.TagExibitionDto;
import API.nhyira.apivitalis.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagExibitionDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagExibitionDto> getTagById(@PathVariable Integer id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagExibitionDto> updateTag(@PathVariable Integer id, @RequestBody TagCreateEditDto tagCreateEditDTO) {
        TagExibitionDto updatedTag = tagService.updateTag(id, tagCreateEditDTO);
        return ResponseEntity.ok(updatedTag);
    }

}