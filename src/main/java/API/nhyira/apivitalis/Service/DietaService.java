package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Dieta;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.DietaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Dieta dieta = repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Dieta"));
        return dieta;
    }

    public List<Dieta> showByMeta(Meta meta) {
        List<Dieta> dietas = repository.findByMetaIdIs(meta);
        return dietas;
    }
}
