package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioMapper;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Repository.RotinaUsuarioRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaUsuarioService {



    private final RotinaUsuarioRepository rotinaTreinoRepository;

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final MetaService metaService;


    public RotinaUsuario show(int id){
        if (id < 1)throw new ErroClienteException("ID");

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.orElseThrow((() -> new  NaoEncontradoException("Usuario")));

        Optional<RotinaUsuario> rotinaTreino = rotinaTreinoRepository.findByUsuarioIdIs(usuario.get());
        rotinaTreino.orElseThrow((() -> new  NaoEncontradoException("Rotina")));
        return rotinaTreino.get();
    }

    public RotinaUsuario create(int idUsuario, int idMeta){
        Usuario  usuario = usuarioService.showUserById(idUsuario);
        Meta meta = metaService.show(idMeta);
        RotinaUsuario rotinaUsuario = new RotinaUsuario();
        rotinaUsuario.setUsuarioId(usuario);
        rotinaUsuario.setMetaId(meta);
        return rotinaTreinoRepository.save(rotinaUsuario);
    }



}
