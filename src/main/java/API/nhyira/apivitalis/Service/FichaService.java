package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaExibitionDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaMapper;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.FichaRepository;
import API.nhyira.apivitalis.Repository.MetaRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import API.nhyira.apivitalis.utils.ListaUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FichaService {
    private final FichaRepository fichaRepository;
    private final UsuarioRepository usuarioRepository;

    public Ficha create(Ficha dto, int idUsuario) {
        if (dto == null) throw new ErroClienteException("Ficha");

        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        dto.setUsuarioId(optUsuario.get());
        Ficha ficha = fichaRepository.save(dto);
        return ficha;
    }

    public Ficha showFicha(int id) {
        if (id < 1) throw new ErroClienteException("ID");

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Ficha> ficha = fichaRepository.findByUsuarioIdIs(usuario.get());
        ficha.orElseThrow(() -> new NaoEncontradoException("Ficha"));
        return ficha.get();
    }

    public Ficha updtFicha(int id, FichaCreateEditDto dto) {
        if (dto == null || id < 1) throw new ErroClienteException("ID");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Ficha> optFicha = fichaRepository.findByUsuarioIdIs(optUsuario.get());
        optFicha.orElseThrow(() -> new NaoEncontradoException("Ficha"));
        Ficha uptFicha = FichaMapper.toEdit(optFicha.get(), dto);
        fichaRepository.save(uptFicha);
        return uptFicha;

    }

    public Boolean delUser(int id) {
        if (id < 1) throw new ErroClienteException("ID");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Ficha> optFicha = fichaRepository.findByUsuarioIdIs(optUsuario.get());
        optFicha.orElseThrow(() -> new NaoEncontradoException("Ficha"));
        fichaRepository.delete(optFicha.get());
        return true;
    }

//    public List<FichaExibitionDto> ordenarTodasFichasPorDeficiencias() {
//        List<Ficha> fichas = fichaRepository.findAll();
//
//        List<Ficha> fichasComDeficiencias = fichas.stream()
//                .filter(ficha -> {
//                    String deficiencia = ficha.getDeficiencias();
//                    return deficiencia != null && !normalize(deficiencia).equals("nao") && !deficiencia.isEmpty();
//                })
//                .collect(Collectors.toList());
//
//        if (fichasComDeficiencias.isEmpty()) {
//            return null;
//        }
//
//        fichasComDeficiencias.sort(Comparator.comparing(ficha -> normalize(ficha.getDeficiencias())));
//
//        return FichaMapper.toDtoList(fichasComDeficiencias);
//    }

    private String normalize(String input) {
        return Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
