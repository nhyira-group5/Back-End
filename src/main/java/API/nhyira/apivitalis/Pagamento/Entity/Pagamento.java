package API.nhyira.apivitalis.Pagamento.Entity;

import API.nhyira.apivitalis.Entity.Assinatura;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Integer idPagamento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "assinaturaId")
    private Assinatura assinatura;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPagamento tipo;

    public enum TipoPagamento {
        PIX
    }
}