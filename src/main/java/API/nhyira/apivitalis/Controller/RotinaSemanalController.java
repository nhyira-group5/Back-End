package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalListExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalMapper;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import API.nhyira.apivitalis.Service.RotinaMensalService;
import API.nhyira.apivitalis.Service.RotinaSemanalService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotinaSemanais")
@RequiredArgsConstructor
public class RotinaSemanalController {
    private final RotinaSemanalService semanalService;

    @GetMapping("/{id}")
    public ResponseEntity<RotinaSemanalExibitionDto> show(@PathVariable int id) {
        if (id <= 0) throw new ErroClienteException("ID");
        RotinaSemanal rotinaSemanal = semanalService.show(id);
        return ResponseEntity.ok(RotinaSemanalMapper.toDto(rotinaSemanal));
    }

    @GetMapping("buscarUsuario/{id}")
    public ResponseEntity<List<RotinaSemanalListExibitionDto>> buscarPorId(@PathVariable int id) {
        if (id <= 0) throw new ErroClienteException("ID");
        List<RotinaSemanal> rotinaSemanal = semanalService.showPorUsuario(id);
        return ResponseEntity.ok(RotinaSemanalMapper.toDtos(rotinaSemanal));
    }

    @GetMapping("/dias-treinados/{idRotinaSemanal}")
    public ResponseEntity<Integer> qtdDiasRealizadosPorSemana(
            @PathVariable int idRotinaSemanal
    ) {
        if (idRotinaSemanal <= 0) throw new ErroClienteException("ID");
        Integer qtd = semanalService.qtdDiasRealizadosPorSemana(idRotinaSemanal);
        return ResponseEntity.ok().body(qtd);
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<RotinaSemanalExibitionDto> concluirRotinaSemanal(
            @PathVariable int id,
            @RequestParam int concluido
    ) {
        if (id <= 0) throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1) throw new ErroClienteException("Concluido");
        RotinaSemanalExibitionDto rm = RotinaSemanalMapper.toDto(semanalService.updateConcluido(id, concluido));
        return ResponseEntity.ok().body(rm);
    }
}
