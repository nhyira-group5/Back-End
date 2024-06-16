package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioExibitionDto {
    private Integer id;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Midia midia;
    private Usuario.TipoUsuario tipo; // Alterado para String
    private UsuarioDto personalId;
    private EnderecoDto academiaId;

    @Data
    public static class EnderecoDto {
        private Integer id;
        private String logradouro;
        private String numero;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;
        private String complemento;
    }

}
