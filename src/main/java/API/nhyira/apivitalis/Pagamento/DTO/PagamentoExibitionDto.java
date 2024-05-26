package API.nhyira.apivitalis.Pagamento.DTO;


import API.nhyira.apivitalis.Pagamento.Entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoExibitionDto {
    private Integer idPagamento;
    private Integer usuarioId;
    private Pagamento.TipoPagamento tipo;
    private AssinaturaDto assinatura;

    @Data
    public static  class AssinaturaDto{
        private Integer idAssinatura;
        private String nome;
        private float valor;
    }
}

