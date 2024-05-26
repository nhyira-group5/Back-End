package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assinatura")
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assinatura")
    private Integer IdAssinatura;

    @Column(name = "nome")
    private String nome;


    @Column(name = "valor")
    private Float valor;
}
