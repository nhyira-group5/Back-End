package API.nhyira.Model;
import java.util.Date;
import jakarta.persistence.*;


@Entity
@Table(name = "personal")
public class PersonalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Integer idPersonal;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "dt_nasc", nullable = false)
    private Date dtNasc;

    @Column(name = "genero", length = 3)
    private String genero;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "email2", length = 100)
    private String email2;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "fk_imagem_personal")
    private MidiaModel midia;


    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
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

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
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

    public MidiaModel getMidia() {
        return midia;
    }

    public void setMidia(MidiaModel midia) {
        this.midia = midia;
    }
}
