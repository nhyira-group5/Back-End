package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLembrete;
    private String conteudo;
    private Date dataLembrete;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

}
