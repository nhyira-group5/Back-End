package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalMapper;
import API.nhyira.apivitalis.Entity.RotinaMensal;
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
    public ResponseEntity<RotinaMensalExibitionDto> show(@PathVariable int id, @RequestParam String mes){
        RotinaMensal rotinaMensal = service.show(id, mes);
        RotinaMensalExibitionDto exibitionDto = RotinaMensalMapper.toDto(rotinaMensal);
        return ResponseEntity.ok(exibitionDto);
    }

}
