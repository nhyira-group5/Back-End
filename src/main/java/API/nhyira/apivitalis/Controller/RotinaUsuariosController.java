package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioMapper;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Service.RotinaUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rotinaUsuarios")
public class RotinaUsuariosController {
    private final RotinaUsuarioService rUserSvr;

    @PostMapping
    public ResponseEntity<RotinaUsuarioExibitionDto> create(
            @RequestBody @Valid RotinaUsuarioCreateEditDto newRUser
    ) {
        RotinaUsuario rUser = RotinaUsuarioMapper.toEntity(newRUser);
        RotinaUsuario rUserCreated = rUserSvr.create(rUser, newRUser.getUsuarioId(), newRUser.getMetaId());
        URI uri = URI.create("/rotinaTreinos/" + rUserCreated.getIdRotinaUsuario());
        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rUserCreated);
        return ResponseEntity.created(uri).body(exibitionDto);
    }

//    @GetMapping
//    public ResponseEntity<List<RotinaUsuarioExibitionDto>> showAll(){
//        List<RotinaTreino> rotina = rUserSvr.showAll();
//        List<RotinaUsuarioExibitionDto> exibitionDtos = RotinaUsuarioMapper.toDto(rotina);
//        return ResponseEntity.ok(exibitionDtos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<RotinaUsuarioExibitionDto> show(
            @PathVariable int id
    ) {
        RotinaUsuario rotina = rUserSvr.show(id);
        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rotina);
        return ResponseEntity.ok(exibitionDto);
    }

//    @PutMapping("/{id}")
//    public  ResponseEntity<RotinaUsuarioExibitionDto> uptd(@PathVariable int id, @RequestBody @Valid RotinaTreinoCreateEditDto dto){
//        RotinaTreino rotina = rUserSvr.updt(dto, id);
//        RotinaUsuarioExibitionDto exibitionDto = RotinaUsuarioMapper.toDto(rotina);
//        return ResponseEntity.ok(exibitionDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RotinaUsuarioExibitionDto> delete(@PathVariable int id){
//        boolean rotina = rUserSvr.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}