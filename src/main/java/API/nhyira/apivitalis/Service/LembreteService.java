package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Lembrete;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.LembreteRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    private final UsuarioRepository usuarioRepository;

    public Lembrete create(Lembrete lembrete, int idUsuario){
        if (lembrete == null)throw new ErroClienteException("Lembrete");
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        lembrete.setUsuarioId(optUsuario.get());
        lembreteRepository.save(lembrete);
        return lembrete;
    }

    public List<Lembrete> shows(int id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        List<Lembrete> lembretes = lembreteRepository.findByUsuarioIdIs(optUsuario.get());
        return lembretes;
    }

    public boolean delete(int id){
        if (!lembreteRepository.existsById(id)){
            throw new NaoEncontradoException("ID");
        }
        lembreteRepository.deleteById(id);
        return true;
    }

}
