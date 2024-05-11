package API.nhyira.apivitalis.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String usuarioReferente;

    private String meta;

    private LocalDate dtRotina;

//    private RotinaTreino fkRotinaTreino;

    private Time horaLembrete;

}
