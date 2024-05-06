package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.Usuario;
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


    public EnderecoExibitionDto create(EnderecoCreateEditDto dto) {
        try {
            if (dto != null) {
                Optional<Usuario> optUsuario = usuarioRepository.findById(dto.getIdPersonal());
                if (optUsuario.isEmpty()) {
                    return null;
                }
                Endereco endereco = EnderecoMapper.toDto(dto);
                endereco.setFkPersonal(optUsuario.get());
                enderecoRepository.save(endereco);
                return EnderecoMapper.toEntity(endereco);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar endereço: " + e.getMessage());
        }
        return null;
    }


    public EnderecoExibitionDto showEndereco(int id) {
        try {
            if (id >= 1) {
                Optional<Usuario> usuario = usuarioRepository.findById(id);
                if (usuario.isPresent()) {
                    Optional<Endereco> endereco = enderecoRepository.findByFkPersonalIs(usuario.get());
                    if (endereco.isPresent()) {
                        return EnderecoMapper.toEntity(endereco.get());
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao mostrar endereço: " + e.getMessage());
        }
        return null;
    }

    public EnderecoExibitionDto updateEndereco(int id, EnderecoCreateEditDto dto) {
        try {
            if (dto != null || id >= 1) {
                Optional<Usuario> optUsuario = usuarioRepository.findById(id);
                if (optUsuario.isPresent()) {
                    Optional<Endereco> optEndereco = enderecoRepository.findByFkPersonalIs(optUsuario.get());
                    if (optEndereco.isPresent()) {
                        Endereco endereco = EnderecoMapper.toEditDto(optEndereco.get(), dto);
                        enderecoRepository.save(endereco);
                        return EnderecoMapper.toEntity(endereco);
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar endereço: " + e.getMessage());
        }
        return null;
    }

    public Boolean deleteUser(int id) {
        try {
            Optional<Usuario> optUsuario = usuarioRepository.findById(id);
            if (optUsuario.isPresent()) {
                Optional<Endereco> optEndereco = enderecoRepository.findByFkPersonalIs(optUsuario.get());
                optEndereco.ifPresent(endereco -> enderecoRepository.delete(endereco));
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao deletar endereço: " + e.getMessage());
        }
    }
}