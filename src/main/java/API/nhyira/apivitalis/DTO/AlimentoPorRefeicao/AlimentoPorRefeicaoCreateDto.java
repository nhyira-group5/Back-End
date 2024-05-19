package API.nhyira.apivitalis.DTO.AlimentoPorRefeicao;

import lombok.Data;

@Data
public class AlimentoPorRefeicaoCreateDto {
    private int qtdAlimentoPorRefeicao;
    private Integer alimentoId;
    private Integer refeicaoId;
    private Integer metricaId;
}
