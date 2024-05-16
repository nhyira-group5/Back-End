package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Exercicio.TagExercicioDto;
import API.nhyira.apivitalis.DTO.Tag.TagExercicioExibitionDto;
import API.nhyira.apivitalis.Service.TagExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tagExercicios")
public class TagExercicioController {

    @Autowired
    private TagExercicioService tagExercicioService;

//    @GetMapping
//    public ResponseEntity<List<TagExercicioExibitionDto>> getAllTagExercicios() {
//        return ResponseEntity.ok(tagExercicioService.getAllTagExercicios());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TagExercicioExibitionDto> getTagExercicioById(@PathVariable Integer id) {
//        return ResponseEntity.ok(tagExercicioService.getTagExercicioById(id));
//    }
}