package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.MuralRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MuralService {
    private final MuralRepository muralRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Mural> showPorUsuario(int id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Usuario"));
        List<Mural> murais = muralRepository.findByUsuarioIdIs(usuario);
        return murais;
    }

    public Mural show(int id){
        Mural mural = muralRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Mural"));
        return mural;
    }

    public List<Mural> showPorData(LocalDate dtPostagem){
        List<Mural> murais = muralRepository.findByDtPostagem(dtPostagem);
        return murais;
    }

    public List<Mural> showPorDataUsuario(LocalDate dtPostagem, int idUsuario){
        Usuario user = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NaoEncontradoException("Usuário"));
        List<Mural> murais = muralRepository.findByDtPostagemAndUsuarioIdIs(dtPostagem, user);
        return murais;
    }
}

