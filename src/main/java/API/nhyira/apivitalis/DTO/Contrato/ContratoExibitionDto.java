package API.nhyira.apivitalis.DTO.Contrato;


import API.nhyira.apivitalis.DTO.Mural.MuralExibitionDto;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratoExibitionDto {


    private Integer idContrato;


    private UsuarioDto usuarioId;


    private UsuarioDto personalId;


    private LocalDate inicioContrato;


    private LocalDate fimContrato;

    private Integer afiliacao;

    @Data
    public static class UsuarioDto{
        private Integer idUsuario;

        private String nickname;

        private String nome;

        private Usuario.TipoUsuario tipo;

        private Midia midia;
        private Meta meta;


    }


}
