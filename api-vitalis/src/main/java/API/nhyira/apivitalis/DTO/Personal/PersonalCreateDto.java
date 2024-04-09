package API.nhyira.apivitalis.DTO.Personal;

import API.nhyira.apivitalis.Entity.Midia;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PersonalCreateDto {

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "O nome do Personal é obrigatorio")
    @Size(max = 20, message = "O nome do Personal deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nome de Personal deve conter pelo menos uma letra maiúscula e um caractere especial")
    private String username;

    @NotBlank(message = "O CPF do Personal é obrigatorio")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank(message ="O nome do Personal é obrigatorio" )
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @Past
    @NotBlank(message = "A data de nascimento é obrigatorio")
    @Column(nullable = false)
    private LocalDate dtNasc;

    @Column(length = 1)
    @Pattern(regexp = "[M|F|N/A]", message = "O gênero deve ser 'M' para masculino, 'F' para feminino")
    private String genero;

    @NotBlank(message = "O email é obrigatorio")
    @Column(nullable = false, unique = true, length = 100)
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Email(message =  "Por favor, insira um endereço de email válido")
    private String email;

    @NotBlank(message = "O email é obrigatorio")
    @Column(nullable = false, unique = true, length = 100)
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Email(message =  "Por favor, insira um endereço de email válido")
    private String email2;

    @NotBlank(message = "A senha é obrigatorio")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$",
            message = "A senha deve conter pelo menos um número, uma letra minúscula, um caractere especial")
    @Size(min = 6, max = 100, message = "A senha deve ter 6 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String senha;


    private Midia midia;


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

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
}
