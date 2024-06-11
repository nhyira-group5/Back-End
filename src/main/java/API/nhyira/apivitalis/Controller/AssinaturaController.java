package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaCreateEditDto;
import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaExibitionDto;
import API.nhyira.apivitalis.Service.AssinaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @Autowired
    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaExibitionDto> createAssinatura(@RequestBody AssinaturaCreateEditDto dto) {
        AssinaturaExibitionDto assinatura = assinaturaService.createAssinatura(dto);
        return ResponseEntity.ok(assinatura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaExibitionDto> getAssinatura(@PathVariable Integer id) {
        AssinaturaExibitionDto assinatura = assinaturaService.getAssinatura(id);
        return ResponseEntity.ok(assinatura);
    }
}