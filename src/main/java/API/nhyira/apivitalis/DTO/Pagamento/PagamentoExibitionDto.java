package API.nhyira.apivitalis.DTO.Pagamento;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoExibitionDto {
    private Integer idHistoricoPagamento;
    private Integer usuarioId;
    private Integer assinatura_id;
    private String tipo;
    private Date data_vencimento;
    private Date data_pagamento;
}