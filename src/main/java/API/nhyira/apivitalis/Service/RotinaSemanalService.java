package API.nhyira.apivitalis.Service;

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

    public Integer qtdDiasRealizadosPorSemana (int idRotinaSemanal) {
        RotinaSemanal rs = show(idRotinaSemanal);
        Optional<Integer> qtd = semanalRepository.qtdDiasRealizadosPorSemana(rs);
        return qtd.orElse(0);
    }

    public RotinaSemanal updateConcluido (int id, int concluido) {
        RotinaSemanal rs = semanalRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Rotina Semanal"));
        if (rs.getConcluido() == concluido) return rs;
        rs.setConcluido(concluido);
        return semanalRepository.save(rs);
    }
}
