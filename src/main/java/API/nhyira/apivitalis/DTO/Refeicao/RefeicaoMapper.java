package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Refeicao;

public class RefeicaoMapper {
    public static Refeicao toEntity(RefeicaoCreateDto dto) {
        Refeicao refeicao = new Refeicao();
        refeicao.setNome(dto.getNome());
        refeicao.setPreparo(dto.getPreparo());

        return refeicao;
    }

    public static RefeicaoExibitionDto toDto (Refeicao entity) {
        RefeicaoExibitionDto refeicaoExibitionDto = new RefeicaoExibitionDto();
        refeicaoExibitionDto.setIdRefeicao(entity.getIdRefeicao());
        refeicaoExibitionDto.setNome(entity.getNome());
        refeicaoExibitionDto.setPreparo(entity.getPreparo());
        refeicaoExibitionDto.setMidia(toMidiaDto(entity.getMidia()));

        return refeicaoExibitionDto;
    }

    public static RefeicaoExibitionDto.MidiaDto toMidiaDto (Midia midia) {
        if (midia == null) return null;

        RefeicaoExibitionDto.MidiaDto midiaDto = new RefeicaoExibitionDto.MidiaDto();
        midiaDto.setIdMidia(midia.getIdMidia());
        midiaDto.setNome(midia.getNome());
        midiaDto.setCaminho(midia.getCaminho());
        midiaDto.setExtensao(midia.getExtensao());

        return midiaDto;
    }
}
