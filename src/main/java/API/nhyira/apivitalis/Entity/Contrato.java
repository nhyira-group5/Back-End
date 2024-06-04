package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContrato;


    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "personalId")
    private Usuario personalId;

    private LocalDate inicioContrato;

    private LocalDate fimContrato;

    private Integer afiliacao;


}
