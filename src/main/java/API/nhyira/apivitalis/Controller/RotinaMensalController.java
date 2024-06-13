package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalMapper;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Service.RotinaMensalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rotinaMensais")
public class RotinaMensalController {
    private final RotinaMensalService service;

    @GetMapping("/{id}/mes")
    public ResponseEntity<RotinaMensalExibitionDto> show(@PathVariable int id, @RequestParam int mes) {
        if (id <= 0) throw new ErroClienteException("ID");
        RotinaMensal rotinaMensal = service.show(id, mes);
        RotinaMensalExibitionDto exibitionDto = RotinaMensalMapper.toDto(rotinaMensal);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotinaMensalExibitionDto> showById(
            @PathVariable int id
    ) {
        if (id <= 0) throw new ErroClienteException("ID");
        RotinaMensal rotinaMensal = service.showById(id);
        RotinaMensalExibitionDto exibitionDto = RotinaMensalMapper.toDto(rotinaMensal);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping("buscarIdUsuario/{id}/mes")
    public ResponseEntity<RotinaMensalExibitionDto> buscarIdUsuario(@PathVariable int id, @RequestParam int mes) {
        if (id <= 0) throw new ErroClienteException("ID");
        RotinaMensal rotinaMensal = service.showPorUsuario(id, mes);
        RotinaMensalExibitionDto exibitionDto = RotinaMensalMapper.toDto(rotinaMensal);
        return ResponseEntity.ok(exibitionDto);
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<RotinaMensalExibitionDto> concluirRotinaMensal(
            @PathVariable int id,
            @RequestParam int concluido
    ) {
        if (id <= 0) throw new ErroClienteException("ID");
        if (concluido < 0 || concluido > 1) throw new ErroClienteException("Concluido");
        RotinaMensalExibitionDto rm = RotinaMensalMapper.toDto(service.updateConcluido(id, concluido));
        return ResponseEntity.ok().body(rm);
    }
}
