package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoMapper;
import API.nhyira.apivitalis.Entity.RotinaTreino;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.RotinaTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotinaTreinoService {


    @Autowired
    private RotinaTreinoRepository rotinaTreinoRepository;

    public RotinaTreino create(RotinaTreino dto){
        if (dto == null)throw new ErroClienteException("Rotina");
        rotinaTreinoRepository.save(dto);
        return dto;
    }

    public List<RotinaTreino> showAll(){
        List<RotinaTreino> rotinasTreino = rotinaTreinoRepository.findAll();
        if (rotinasTreino.isEmpty()) throw new SemConteudoException("Rotinas");

        return rotinasTreino;
    }

    public RotinaTreino show(int id){
        if (id < 1)throw new ErroClienteException("ID");

        Optional<RotinaTreino> rotinaTreino = rotinaTreinoRepository.findById(id);
        rotinaTreino.orElseThrow(() -> new  NaoEncontradoException("Rotina de treino"));
        return rotinaTreino.get();
    }


    public RotinaTreino updt(RotinaTreinoCreateEditDto dto, int id){
        if (dto == null) throw new ErroClienteException("Rotina de Treino");
        if (id < 1)throw new ErroClienteException("ID");

        Optional<RotinaTreino> optRotinaTreino = rotinaTreinoRepository.findById(id);
        optRotinaTreino.orElseThrow(() -> new NaoEncontradoException("Rotina de treino"));
        RotinaTreino uptRotina = RotinaTreinoMapper.toEdit(optRotinaTreino.get(), dto);
        rotinaTreinoRepository.save(uptRotina);
        return uptRotina;
    }

    public boolean delete(int id) {
        if (!rotinaTreinoRepository.existsById(id)) {
            throw new NaoEncontradoException("Id");
        }
        rotinaTreinoRepository.deleteById(id);
        return true;
    }

}
