package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.DietaRepository;
import jdk.dynalink.linker.LinkerServices;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DietaService {

    private final DietaRepository repository;



    public List<Dieta> showAll(){
        List<Dieta> dietaList = repository.findAll();
        if (dietaList.isEmpty()) throw new SemConteudoException("Dieta");
        return dietaList;
    }

    public Dieta show(int id){
        if (id < 1) throw new ErroClienteException("ID");
        Optional<Dieta> dieta = repository.findById(id);
        dieta.orElseThrow(() -> new NaoEncontradoException("Dieta"));
        return dieta.get();
    }


}
