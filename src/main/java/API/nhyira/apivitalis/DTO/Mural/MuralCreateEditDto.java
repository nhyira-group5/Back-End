package API.nhyira.apivitalis.DTO.Mural;

import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MuralCreateEditDto {

    private Integer usuarioId;


    private Integer midiaId;

    private LocalDate dtPostagem;

}
