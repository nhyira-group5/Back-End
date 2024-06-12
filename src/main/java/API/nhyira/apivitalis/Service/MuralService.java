package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.MuralRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MuralService {

    private final MuralRepository muralRepository;

    private final UsuarioRepository usuarioRepository;

    public Mural showPorUsuario(int id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Mural> optionalMural = muralRepository.findByUsuarioIdIs(usuario.get());
        optionalMural.orElseThrow(() -> new NaoEncontradoException("Mural"));
        return optionalMural.get();
    }

    public Mural show(int id){
        Optional<Mural> optionalMural = muralRepository.findById(id);
        optionalMural.orElseThrow(() -> new NaoEncontradoException("Mural"));
        return optionalMural.get();
    }

    public List<Mural> showPorData(LocalDate dtPostagem){
//        Mural muralPorId = show(id);
        String data = dtPostagem.toString();
        List<Mural> mural = muralRepository.findByDtPostagemStartingWith(data);
//        mural.orElseThrow(() -> new NaoEncontradoException("mural"));
        return mural;
    }

}

