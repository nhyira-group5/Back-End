package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaSemanalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaSemanalService {

    private final RotinaSemanalRepository semanalRepository;

    public RotinaSemanal show(int id){
        Optional<RotinaSemanal> rotinaSemanal = semanalRepository.findById(id);
        rotinaSemanal.orElseThrow(() -> new NaoEncontradoException("Rotina semanal"));
        return rotinaSemanal.get();
    }


}
