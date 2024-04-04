package API.nhyira.apivitalis.Auth.Usuario.DTO;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DetailsUsuario implements UserDetails {

//    @Column(name = "username", nullable = false, unique = true, length = 20)
//    @NotBlank(message = "O nome de usuário é obrigatório")
//    @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nome de usuário deve conter pelo menos uma letra maiúscula e um caractere especial")
//    private String username;
//
//    private String cpf;
//
//   private String nome;
//
//    private LocalDate dtNasc;
//
//    private String genero;
//
//    @NotBlank(message = "O email é obrigatório")
//    @Email(message = "Por favor, insira um endereço de email válido")
//    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
//    @Column(length = 100)
//    private String email;
//
//    private String email2;
//
//    @NotBlank(message = "A senha é obrigatória")
//    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$",
//            message = "A senha deve conter pelo menos um número, uma letra minúscula, um caractere especial")
//    @Column(length = 100)
//    private String senha;
//
//   private Float peso;
//
//    private Float altura;
//
//    private Midia midia;
//
//    private Meta meta;
//
//    public DetailsUsuario(String username, String cpf, String nome, LocalDate dtNasc, String genero, String email, String email2, String senha, Float peso, Float altura, Midia midia, Meta meta) {
//        this.username = username;
//        this.cpf = cpf;
//        this.nome = nome;
//        this.dtNasc = dtNasc;
//        this.genero = genero;
//        this.email = email;
//        this.email2 = email2;
//        this.senha = senha;
//        this.peso = peso;
//        this.altura = altura;
//        this.midia = midia;
//        this.meta = meta;
//    }
//
//    public DetailsUsuario(String username, String email, String senha) {
//        this.username = username;
//        this.email = email;
//        this.senha = senha;
//    }

    private final Usuario usuario;

    public DetailsUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

//    @Override
//    public String getPassword() {
//        return senha;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public String getEmail() {
//        return email;
//    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
