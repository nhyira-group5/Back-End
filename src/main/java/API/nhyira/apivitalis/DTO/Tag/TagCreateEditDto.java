package API.nhyira.apivitalis.DTO.Tag;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class TagCreateEditDto {

    @NotNull
    @Size(max = 100)
    private String nome;
}
