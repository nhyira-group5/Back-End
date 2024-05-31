package API.nhyira.apivitalis.DTO.Contrato;


import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratoCreateEditDto {



    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer personalId;


    @FutureOrPresent
    @NotNull
    private LocalDate inicioContrato;


    @FutureOrPresent
    private LocalDate fimContrato;



}
