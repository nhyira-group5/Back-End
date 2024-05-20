package API.nhyira.apivitalis.DTO.RotinaMensal;


import API.nhyira.apivitalis.DTO.RotinaUsuario.RotinaUsuarioExibitionDto;
import API.nhyira.apivitalis.Entity.Meta;
import lombok.Data;

@Data
public class RotinaMensalExibitionDto {

    private int id;

    private RotinaUsuarioDto rotinaUsuarioId;

    private String mes;

    private int ano;

    @Data
    public static class RotinaUsuarioDto{
        private int id;

        private Meta metaId;
    }

}
