package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Meta.MetaExibitionDto;
import API.nhyira.apivitalis.DTO.Meta.MetaMapper;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Service.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MetaService metaService;

    @GetMapping("/{id}")
    public ResponseEntity<MetaExibitionDto> show(@PathVariable int id){
        Meta meta = metaService.show(id);
        MetaExibitionDto exibitionDto = MetaMapper.toDto(meta);
        return ResponseEntity.ok(exibitionDto);
    }

    @GetMapping()
    public ResponseEntity<List<MetaExibitionDto>> shows(){
        List<Meta> metas = metaService.shows();
        if(metas.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(MetaMapper.toDto(metas));
    }
}
