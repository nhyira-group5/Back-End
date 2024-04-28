package API.nhyira.apivitalis.Service;





import API.nhyira.apivitalis.Entity.EnderecoModel;
import API.nhyira.apivitalis.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoModel salvarEndereco(EnderecoModel endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<EnderecoModel> buscarEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    public List<EnderecoModel> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public EnderecoModel atualizarEndereco(Integer id, EnderecoModel novoEndereco) {
        Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            EnderecoModel enderecoExistente = enderecoOptional.get();
            enderecoExistente.setLogradouro(novoEndereco.getLogradouro());
            enderecoExistente.setNumero(novoEndereco.getNumero());
            enderecoExistente.setCidade(novoEndereco.getCidade());
            enderecoExistente.setEstado(novoEndereco.getEstado());
            enderecoExistente.setCep(novoEndereco.getCep());
            return enderecoRepository.save(enderecoExistente);
        } else {
            throw new IllegalArgumentException("Endereço não encontrado com o ID: " + id);
        }
    }

    public void deletarEndereco(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
