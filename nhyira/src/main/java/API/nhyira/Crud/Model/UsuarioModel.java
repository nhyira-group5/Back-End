package API.nhyira.Crud.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nome de usuário deve conter pelo menos uma letra maiúscula e um caractere especial")
    private String username;


    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(name = "dt_nasc", nullable = false)
    private LocalDate dtNasc;

    @Column(name = "genero", length = 1)
    @Pattern(regexp = "[M|F|N/A]", message = "O gênero deve ser 'M' para masculino, 'F' para feminino")
    private String genero;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Por favor, insira um endereço de email válido")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Size(max = 100, message = "O segundo email deve ter no máximo 100 caracteres")
    @Email(message = "Por favor, insira um endereço de email válido")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Por favor, insira um endereço de email válido")
    @Column(name = "email2", length = 100)
    private String email2;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$",
            message = "A senha deve conter pelo menos um número, uma letra minúscula, um caractere especial")
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "peso", nullable = false)
    @DecimalMin(value = "0", inclusive = true, message = "O peso deve ser um número positivo ou zero")
    private Float peso;

    @Column(name = "altura", nullable = false)
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "A altura deve ser um número válido com um ponto decimal opcional")
    private Float altura;


    @ManyToOne
    @JoinColumn(name = "fk_imagem_usuario")
    private MidiaModel midia;

    @ManyToOne
    @JoinColumn(name = "fk_meta")
    private MetaModel meta;

    // Getters e Setters omitidos para brevidade


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public MidiaModel getMidia() {
        return midia;
    }

    public void setMidia(MidiaModel midia) {
        this.midia = midia;
    }

    public MetaModel getMeta() {
        return meta;
    }

    public void setMeta(MetaModel meta) {
        this.meta = meta;
    }
}
