package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cartao", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bandeira", "cvv"})
})
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCartao")
    private Integer idCartao;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "nome_titular", unique = true)
    private String nomeTitular;

    private String validade;

    private Integer cvv;

    private String bandeira;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}