package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class RotinaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaUsuario;
    private Integer rotinaAlternativa;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "metaId")
    private Meta metaId;
}