package API.nhyira.apivitalis.Pagamento.DTO;


import API.nhyira.apivitalis.Pagamento.Entity.Pagamento;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PagamentoCreateEditDto {
    private Integer idPagamento;
    private Integer usuarioId;
    private Pagamento.TipoPagamento tipo;
    private Integer assinaturaId;
}
