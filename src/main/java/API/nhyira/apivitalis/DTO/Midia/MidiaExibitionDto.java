package API.nhyira.apivitalis.DTO.Midia;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MidiaExibitionDto {

    private Integer idMidia;

    private String nome;

    private String caminho;

    private String extensao;
}