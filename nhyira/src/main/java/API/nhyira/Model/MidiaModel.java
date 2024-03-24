package API.nhyira.Model;



import jakarta.persistence.*;

@Entity
@Table(name = "midia")
public class MidiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_midia")
    private Integer idMidia;

    @Column(name = "nome_arquivo", nullable = false, length = 100)
    private String nomeArquivo;

    @Lob
    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @Column(name = "extensao", nullable = false, length = 10)
    private String extensao;

    // Getters and setters
    public Integer getIdMidia() {
        return idMidia;
    }

    public void setIdMidia(Integer idMidia) {
        this.idMidia = idMidia;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
}

