package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class UsuarioCreateEditDto {
    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nome de usuário deve conter pelo menos uma letra maiúscula e um caractere especial")
    private String username;

    @NotBlank(message = "O CPF é obrigatório")
//    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    @Column(nullable = false, unique = true, length = 14)
    @CPF
    private String cpf;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(nullable = false)
    @Past
    private LocalDate dtNasc;

    @Column(length = 1)
    @Pattern(regexp = "[M|F|N/A]", message = "O gênero deve ser 'M' para masculino, 'F' para feminino")
    private String genero;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Por favor, insira um endereço de email válido")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Email(message = "Por favor, insira um endereço de email válido")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Column(nullable = false, length = 100)
    private String email2;

    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$",
            message = "A senha deve conter pelo menos um número, uma letra minúscula, um caractere especial")
    @Column(nullable = false, length = 100)
    private String senha;

    private Usuario.TipoUsuario tipo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    public Usuario.TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(Usuario.TipoUsuario tipo) {
        this.tipo = tipo;
    }


}
