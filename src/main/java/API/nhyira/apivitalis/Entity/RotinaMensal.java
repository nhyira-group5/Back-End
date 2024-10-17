package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class RotinaMensal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaMensal;

    @ManyToOne
    @JoinColumn(name = "rotinaUsuarioId")
    private RotinaUsuario rotinaUsuarioId;

    private int mes;
    private int ano;
    private int concluido;

    @OneToMany(mappedBy = "rotinaMensalId")
    private List<RotinaSemanal> rotinaSemanals;
}
