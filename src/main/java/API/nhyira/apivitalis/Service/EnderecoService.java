package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import API.nhyira.apivitalis.Entity.Endereco;
import API.nhyira.apivitalis.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoExibitionDto;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoMapper;
import API.nhyira.apivitalis.DTO.Endereco.EnderecoCreateEditDto;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Endereco create(Endereco dto, int id) {
        if (dto == null) throw new ErroClienteException("Endereco");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        dto.setPersonalId(optUsuario.get());
        enderecoRepository.save(dto);
        return dto;
    }


    public Endereco showEndereco(int id) {
        if (id < 1) throw new ErroClienteException("ID");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Endereco> optEndereco = enderecoRepository.findByPersonalIdIs(optUsuario.get());
        optEndereco.orElseThrow(() -> new  NaoEncontradoException("Endereco"));
        return optEndereco.get();
    }

    public Endereco updateEndereco(int id, EnderecoCreateEditDto dto) {

        if (dto == null) throw new ErroClienteException("Endereco");
        if (id < 1)throw new ErroClienteException("ID");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Endereco> optEndereco = enderecoRepository.findByPersonalIdIs(optUsuario.get());
        optEndereco.orElseThrow(() -> new  NaoEncontradoException("Endereco"));
        Endereco endereco = EnderecoMapper.toEditDto(optEndereco.get(), dto);
        enderecoRepository.save(endereco);
        return endereco;

    }

    public Boolean deleteUser(int id) {
        if(id < 1)throw new NaoEncontradoException("ID");

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        Optional<Endereco> optEndereco = enderecoRepository.findByPersonalIdIs(optUsuario.get());
        optEndereco.orElseThrow(() -> new NaoEncontradoException("Endereco"));
        enderecoRepository.delete(optEndereco.get());
            return true;
    }
}