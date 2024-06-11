package API.nhyira.apivitalis.DTO.Refeicao;

import API.nhyira.apivitalis.DTO.AlimentoPorRefeicao.AlimentoPorRefeicaoExibitionDto;
import API.nhyira.apivitalis.Entity.Alimento;
import API.nhyira.apivitalis.Entity.AlimentoPorRefeicao;
import API.nhyira.apivitalis.Entity.Metrica;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RefeicaoExibition {
    private Integer idRefeicao;

    @Size(min = 1, max = 100)
    private String nome;

    @Size(min = 1, max = 550)
    private String preparo;

    private RefeicaoExibitionDto.MidiaDto midia;

    private List<AlimentoPorRefeicaoDto> alimentoPorRefeicao;




    @Data
    public static class AlimentoPorRefeicaoDto{
        private Integer idAlimentoRefeicao;
        private int qtdAlimento;
        private Alimento alimento;
        private Metrica metrica;
    }

}
