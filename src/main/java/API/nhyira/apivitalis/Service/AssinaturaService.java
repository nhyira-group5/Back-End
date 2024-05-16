package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaCreateEditDto;
import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaExibitionDto;
import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaMapper;
import API.nhyira.apivitalis.Entity.Assinatura;
import API.nhyira.apivitalis.Repository.AssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private AssinaturaMapper assinaturaMapper;

    public AssinaturaExibitionDto createAssinatura(AssinaturaCreateEditDto dto) {
        Assinatura assinatura = assinaturaMapper.toEntity(dto);
        assinatura = assinaturaRepository.save(assinatura);
        return assinaturaMapper.toExibitionDto(assinatura);
    }

    public AssinaturaExibitionDto getAssinatura(Integer id) {
        Assinatura assinatura = assinaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        return assinaturaMapper.toExibitionDto(assinatura);
    }

    public List<AssinaturaExibitionDto> getAllAssinaturas() {
        List<Assinatura> assinaturas = assinaturaRepository.findAll();
        return assinaturas.stream().map(assinaturaMapper::toExibitionDto).collect(Collectors.toList());
    }

    public void deleteAssinatura(Integer id) {
        assinaturaRepository.deleteById(id);
    }

    public AssinaturaExibitionDto updateAssinatura(Integer id, AssinaturaCreateEditDto dto) {
        Assinatura assinatura = assinaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        assinatura.setNome(dto.getNome());
        assinatura.setValor(dto.getValor().floatValue());
        assinatura = assinaturaRepository.save(assinatura);
        return assinaturaMapper.toExibitionDto(assinatura);
    }
}