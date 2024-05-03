package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoCreateEditDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaTreino.RotinaTreinoMapper;
import API.nhyira.apivitalis.Entity.RotinaTreino;
import API.nhyira.apivitalis.Repository.RotinaTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotinaTreinoService {


    @Autowired
    private RotinaTreinoRepository rotinaTreinoRepository;

    public RotinaTreinoExibitionDto create(RotinaTreinoCreateEditDto dto){
        if (dto == null){
            return null;
        }
        RotinaTreino rotinaTreino = RotinaTreinoMapper.toDto(dto);
         rotinaTreinoRepository.save(rotinaTreino);
        return RotinaTreinoMapper.toEntity(rotinaTreino);
    }

    public List<RotinaTreinoExibitionDto> showAll(){
        List<RotinaTreino> rotinaTreino = rotinaTreinoRepository.findAll();
        if (rotinaTreino.isEmpty()){
            return null;
        }
        return RotinaTreinoMapper.toEntities(rotinaTreino);
    }

    public RotinaTreinoExibitionDto show(int id){
        if (id <=0){
            return null;
        }
        Optional<RotinaTreino> rotinaTreino = rotinaTreinoRepository.findById(id);
        return rotinaTreino.map(RotinaTreinoMapper::toEntity).orElse(null);
    }


    public RotinaTreinoExibitionDto updt(RotinaTreinoCreateEditDto dto, int id){
        if (dto == null || id < 0){
            return null;
        }
        Optional<RotinaTreino> optRotinaTreino = rotinaTreinoRepository.findById(id);
        if (optRotinaTreino.isEmpty()){
            return null;
        }
        RotinaTreino uptRotina = RotinaTreinoMapper.toEdit(optRotinaTreino.get(), dto);
        rotinaTreinoRepository.save(uptRotina);
        return RotinaTreinoMapper.toEntity(uptRotina) ;
    }

    public boolean delete(int id){
        if(id <= 0){
            return false;
        }
        if(rotinaTreinoRepository.existsById(id)){
            rotinaTreinoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
