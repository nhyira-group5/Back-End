package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;

    public Meta show(int id){
        if (id < 1)throw new ErroClienteException("Meta");
        Optional<Meta> optMeta = metaRepository.findById(id);
        optMeta.orElseThrow(() -> new NaoEncontradoException("Meta"));
        return optMeta.get();
    }


}
