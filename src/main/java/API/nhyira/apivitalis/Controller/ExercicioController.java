package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioCreateEditDto;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibitionDto;
import API.nhyira.apivitalis.Service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<List<ExercicioExibitionDto>> getAllExercicios() {
        return ResponseEntity.ok(exercicioService.getAllExercicios());
    }


    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = exercicioService.uploadImage(file);
        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/uploadVideo")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoUrl = exercicioService.uploadVideo(file);
        return ResponseEntity.ok(videoUrl);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExercicioExibitionDto> getExercicioById(@PathVariable Integer id) {
        return ResponseEntity.ok(exercicioService.getExercicioById(id));
    }

    @PostMapping
    public ResponseEntity<ExercicioExibitionDto> createExercicio(@RequestBody ExercicioCreateEditDto exercicioDTO) {
        return ResponseEntity.ok(exercicioService.createExercicio(exercicioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioExibitionDto> updateExercicio(@PathVariable Integer id, @RequestBody ExercicioCreateEditDto exercicioDTO) {
        return ResponseEntity.ok(exercicioService.updateExercicio(id, exercicioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicio(@PathVariable Integer id) {
        exercicioService.deleteExercicio(id);
        return ResponseEntity.noContent().build();
    }
}