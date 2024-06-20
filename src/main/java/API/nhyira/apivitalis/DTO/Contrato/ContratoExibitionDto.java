package API.nhyira.apivitalis.DTO.Contrato;


import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratoExibitionDto {


    private Integer idContrato;


    private MuralExibitionDto.UsuarioDto usuarioId;


    private MuralExibitionDto.UsuarioDto personalId;


    private LocalDate inicioContrato;


    private LocalDate fimContrato;

    private Integer afiliacao;




}
