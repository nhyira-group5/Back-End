package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFicha;

    private Integer problemaCardiaco;

    private Integer dorPeitoAtividade;

    private Integer dorPeitoUltimoMes;

    private Integer problemaOsseoArticular;

    private Integer medicamentoPressaoCoracao;

    private Integer impedimentoAtividade;

    private Float peso;

    private float altura;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    public double getIMC(){
        return peso/(altura* altura);
    }
}
