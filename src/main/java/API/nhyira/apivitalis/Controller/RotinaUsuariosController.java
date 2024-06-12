package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioMapper;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Service.RotinaUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/rotinaUsuarios")
public class RotinaUsuariosController {

    @Autowired
    private RotinaUsuarioService rotinaUsuarioService;

    @PostMapping
    public ResponseEntity<RotinaUsuarioExibitionDto> create(@RequestBody @Valid RotinaUsuarioCreateEditDto dto){
        RotinaUsuario rotina = rotinaUsuarioService.create(dto.getUsuarioId(), dto.getMetaId());
        URI uri = URI.create("/rotinaTreinos/"+ rotina);
        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rotina);
        return ResponseEntity.created(uri).body(exibitionDto);

    }

//    @GetMapping
//    public ResponseEntity<List<RotinaUsuarioExibitionDto>> showAll(){
//        List<RotinaTreino> rotina = rotinaUsuarioService.showAll();
//        List<RotinaUsuarioExibitionDto> exibitionDtos = RotinaUsuarioMapper.toDto(rotina);
//        return ResponseEntity.ok(exibitionDtos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<RotinaUsuarioExibitionDto> show(@PathVariable int id){
        RotinaUsuario rotina = rotinaUsuarioService.show(id);
        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rotina);
        return ResponseEntity.ok(exibitionDto);
    }


//    @PutMapping("/{id}")
//    public  ResponseEntity<RotinaUsuarioExibitionDto> uptd(@PathVariable int id, @RequestBody @Valid RotinaTreinoCreateEditDto dto){
//        RotinaTreino rotina = rotinaUsuarioService.updt(dto, id);
//        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rotina);
//        return ResponseEntity.ok(exibitionDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RotinaUsuarioExibitionDto> delete(@PathVariable int id){
//        boolean rotina = rotinaUsuarioService.delete(id);
//        return ResponseEntity.noContent().build();
//    }


}
