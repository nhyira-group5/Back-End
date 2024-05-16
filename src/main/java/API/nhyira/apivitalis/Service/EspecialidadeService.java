package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository repository;

    public List<Especialidade> showall(){
        List<Especialidade> especialidades = repository.findAll();
        if (especialidades.isEmpty()){
            throw new SemConteudoException("Especialidade");
        }
        return especialidades;
    }

    public Especialidade show(int id){
        Optional<Especialidade> especialidade = repository.findById(id);
        especialidade.orElseThrow(() -> new NaoEncontradoException("Id"));
        return especialidade.get();
    }


}
