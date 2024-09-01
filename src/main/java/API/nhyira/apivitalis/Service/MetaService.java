package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaService {
    private final MetaRepository metaRepository;

    public Meta show(int id){
        Meta optMeta = metaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Meta"));
        return optMeta;
    }
    
    public List<Meta> shows(){
        List<Meta> metas = metaRepository.findAll();
        return metas;
    }
}
