package API.nhyira.apivitalis.Service;





import API.nhyira.apivitalis.Entity.Endereco;
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

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> buscarEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    public List<Endereco> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco atualizarEndereco(Integer id, Endereco novoEndereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco enderecoExistente = enderecoOptional.get();
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
