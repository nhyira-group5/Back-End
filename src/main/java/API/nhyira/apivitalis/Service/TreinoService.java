package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public Treino show(int id){
        Optional<Treino> optTreino = treinoRepository.findById(id);
        optTreino.orElseThrow(() -> new NaoEncontradoException("Treino"));
        return optTreino.get();
    }


}
