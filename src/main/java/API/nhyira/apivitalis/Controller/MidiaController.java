package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Midia.MidiaCreateEditDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaExibitionDto;
import API.nhyira.apivitalis.Service.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/midias")
public class MidiaController {
    @Autowired
    private MidiaService midiaService;

    @GetMapping
    public ResponseEntity<List<MidiaExibitionDto>> getAllMidias() {
        return ResponseEntity.ok(midiaService.getAllMidias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MidiaExibitionDto> getMidiaById(@PathVariable Integer id) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(midiaService.getMidiaById(id));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = midiaService.uploadImage(file);
        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/uploadVideo")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoUrl = midiaService.uploadVideo(file);
        return ResponseEntity.ok(videoUrl);
    }

    @PostMapping("/salvarMidia")
    public ResponseEntity<MidiaExibitionDto> createMidia(
            @RequestBody MidiaCreateEditDto midiaDTO
    ) {
        return ResponseEntity.status(201).body(midiaService.createMidia(midiaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MidiaExibitionDto> updateMidia(
            @PathVariable Integer id,
            @RequestBody MidiaCreateEditDto midiaDTO
    ) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(midiaService.updateMidia(id, midiaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMidia(@PathVariable Integer id) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        midiaService.deleteMidia(id);
        return ResponseEntity.noContent().build();
    }
}