package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaSemanalRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaSemanalService {

    private final RotinaSemanalRepository semanalRepository;
    private final UsuarioRepository usuarioRepository;

    public RotinaSemanal show(int id){
        Optional<RotinaSemanal> rotinaSemanal = semanalRepository.findById(id);
        rotinaSemanal.orElseThrow(() -> new NaoEncontradoException("Rotina semanal"));
        return rotinaSemanal.get();
    }

    public List<RotinaSemanal> showPorUsuario(int id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        return semanalRepository.buscarPorIdUsuario(id);
    }


}
