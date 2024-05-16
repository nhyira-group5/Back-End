package API.nhyira.apivitalis.DTO.Pagamento;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PagamentoCreateEditDto {
    private Integer usuarioId;
    private Integer assinatura_id;
    private String tipo;
    private Date data_vencimento;
    private Date data_pagamento;
}