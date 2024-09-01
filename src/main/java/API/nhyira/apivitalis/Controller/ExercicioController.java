package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibitionDto;
import API.nhyira.apivitalis.DTO.Exercicio.ExercicioMapper;
import API.nhyira.apivitalis.Entity.Exercicio;
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
    public ResponseEntity<List<ExercicioExibition>> getAllExercicios() {
        return ResponseEntity.ok(ExercicioMapper.toDto(exercicioService.getAllExercicios()));
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
    public ResponseEntity<ExercicioExibition> getExercicioById(@PathVariable Integer id) {
        return ResponseEntity.ok(exercicioService.getExercicioById(id));
    }

    @GetMapping("buscarPorTreino/{id}")
    public ResponseEntity<ExercicioExibition> buscarPorTreino(@PathVariable Integer id) {
        Exercicio exercicio = exercicioService.showPorRotina(id);
        return ResponseEntity.ok(ExercicioMapper.toDto(exercicio));
    }

}