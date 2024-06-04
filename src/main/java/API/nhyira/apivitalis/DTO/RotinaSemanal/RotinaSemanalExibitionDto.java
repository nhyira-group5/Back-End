package API.nhyira.apivitalis.DTO.RotinaSemanal;


import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class RotinaSemanalExibitionDto {

    private Integer id;
    private RotinaMensalExibitionDto rotinaMensalId;
    private Integer concluido;
    private List<RotinaDiariaDto> rotinaDiariaDtos;


    @Data
    public static class RotinaMensalExibitionDto{

        private int id;

        private int mes;

        private int ano;
    }

    @Data
    public static class RotinaDiariaDto{

        private Integer idRotinaDiaria;

        private Integer concluido;
    }

}
