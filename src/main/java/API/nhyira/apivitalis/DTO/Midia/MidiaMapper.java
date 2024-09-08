package API.nhyira.apivitalis.DTO.Midia;

import API.nhyira.apivitalis.Entity.Midia;

public class MidiaMapper {

    public static MidiaExibitionDto toDTO(Midia midia) {
        MidiaExibitionDto dto = new MidiaExibitionDto();
        dto.setIdMidia(midia.getIdMidia());
        dto.setNome(midia.getNome());
        dto.setCaminho(midia.getCaminho());
        dto.setExtensao(midia.getExtensao());
        dto.setTipo(midia.getTipo());
        return dto;
    }

    public static Midia toEntity(MidiaCreateEditDto midiaDTO) {
        Midia midia = new Midia();
        midia.setNome(midiaDTO.getNome());
        midia.setCaminho(midiaDTO.getCaminho());
        midia.setExtensao(midiaDTO.getExtensao());
        midia.setTipo(midiaDTO.getTipo());
        return midia;
    }
}
