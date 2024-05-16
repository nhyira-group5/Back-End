package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.EspecialidadePorMeta;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.EspecialidadePorMetaRepository;
import API.nhyira.apivitalis.Repository.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadePorMetaService {

    private final EspecialidadePorMetaRepository repository;

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadePorMeta show(int id){
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id);
        especialidade.orElseThrow(() ->  new NaoEncontradoException("Especialidade"));

        Optional<EspecialidadePorMeta> especialidadePorMeta = repository.findByEspecialidadeId(especialidade.get());
        especialidadePorMeta.orElseThrow(() -> new  NaoEncontradoException("Especialidade Por meta"));
        return especialidadePorMeta.get();
    }


}
