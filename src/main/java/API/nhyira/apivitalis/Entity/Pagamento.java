package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoricoPagamento;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "assinatura_id")
    private Integer assinatura_id;

    private String tipo;


    private Date data_vencimento;

    private Date data_pagamento;
}
