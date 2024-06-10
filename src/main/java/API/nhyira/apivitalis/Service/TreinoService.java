package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final RotinaDiariaService rotinaDiariaService;

//    public Treino show(int id){
//        Optional<Treino> optTreino = treinoRepository.findById(id);
//        optTreino.orElseThrow(() -> new NaoEncontradoException("Treino"));
//        return optTreino.get();
//    }

    public List<Treino> show(int id){
        RotinaDiaria rotinaDiaria = rotinaDiariaService.show(id);
        List<Treino> optTreino = treinoRepository.findByRotinaDiariaIdIs(rotinaDiaria);
        return optTreino;
    }




}
