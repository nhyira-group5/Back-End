package API.nhyira.apivitalis.DTO.Tag;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TagCreateEditDto {

    @NotNull
    @Size(max = 100)
    private String nome;
}
