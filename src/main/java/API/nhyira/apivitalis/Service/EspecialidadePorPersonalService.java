package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.EspecialidadePorPersonal;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.EspecialidadePorPersonalRepository;
import API.nhyira.apivitalis.Repository.EspecialidadeRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadePorPersonalService {


    private final EspecialidadePorPersonalRepository repository;

    private final EspecialidadeRepository especialidadeRepository;
    private final UsuarioRepository usuarioRepository;
    public EspecialidadePorPersonal create(EspecialidadePorPersonal novaEspecialidade, int idPersonal, int idEspecialidade){
        if (novaEspecialidade == null)throw new NaoEncontradoException("Especialidade");
        Optional<Especialidade> especialidade = especialidadeRepository.findById(idEspecialidade);
        especialidade.orElseThrow(() -> new NaoEncontradoException("Especialidade"));
        novaEspecialidade.setEspecialidadeId(especialidade.get());

        Optional<Usuario> usuario = usuarioRepository.findById(idPersonal);
        usuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        novaEspecialidade.setPersonalId(usuario.get());
        repository.save(novaEspecialidade);
        return novaEspecialidade;
    }

    public List<EspecialidadePorPersonal> show(int id){
        if (id < 1)throw new ErroClienteException("ID");
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));

        List<EspecialidadePorPersonal> porPersonals = repository.findByPersonalIdIs(usuario.get());
        if (porPersonals.isEmpty())throw new NaoEncontradoException("ID");

        return porPersonals;
    }
}
