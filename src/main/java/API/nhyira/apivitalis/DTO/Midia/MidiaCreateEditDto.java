package API.nhyira.apivitalis.DTO.Midia;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MidiaCreateEditDto {

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotNull
    @Size(max = 500)
    private String caminho;

    @NotNull
    @Size(max = 10)
    private String extensao;

    private String tipo;
}