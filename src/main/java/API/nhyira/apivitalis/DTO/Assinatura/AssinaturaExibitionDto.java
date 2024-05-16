package API.nhyira.apivitalis.DTO.Assinatura;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssinaturaExibitionDto {
    private Integer id;
    private String nome;
    private Double valor;
}