package API.nhyira.apivitalis.DTO.Assinatura;

import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaExibitionDto;
import API.nhyira.apivitalis.DTO.Assinatura.AssinaturaCreateEditDto;
import API.nhyira.apivitalis.Entity.Assinatura;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaMapper {

    public AssinaturaExibitionDto toExibitionDto(Assinatura assinatura) {
        AssinaturaExibitionDto dto = new AssinaturaExibitionDto();
        dto.setId(assinatura.getIdAssinatura());
        dto.setNome(assinatura.getNome());
        dto.setValor(assinatura.getValor().doubleValue());
        return dto;
    }

    public Assinatura toEntity(AssinaturaCreateEditDto dto) {
        Assinatura assinatura = new Assinatura();
        assinatura.setNome(dto.getNome());
        assinatura.setValor(dto.getValor().floatValue());
        return assinatura;
    }
}