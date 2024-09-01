package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Mural {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMural;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    @OneToOne
    @JoinColumn(name = "midiaId")
    private Midia midiaId;

    private LocalDate dtPostagem;

}
