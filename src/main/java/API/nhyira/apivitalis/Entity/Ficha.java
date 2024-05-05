package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "ficha")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFicha;

    private Integer bebe;

    private Integer fuma;

    private String deficiencias;

    private String problemasCaridiacos;
    private String doencasRespiratorias;
    private Float peso;
    private float altura;
    private String meta;


    @ManyToOne
    @JoinColumn(name = "Usuario")
    private Usuario fkUsuario;


    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getBebe() {
        return bebe;
    }

    public void setBebe(Integer bebe) {
        this.bebe = bebe;
    }

    public Integer getFuma() {
        return fuma;
    }

    public void setFuma(Integer fuma) {
        this.fuma = fuma;
    }

    public String getDeficiencias() {
        return deficiencias;
    }

    public void setDeficiencias(String deficiencias) {
        this.deficiencias = deficiencias;
    }

    public String getProblemasCaridiacos() {
        return problemasCaridiacos;
    }

    public void setProblemasCaridiacos(String problemasCaridiacos) {
        this.problemasCaridiacos = problemasCaridiacos;
    }

    public String getDoencasRespiratorias() {
        return doencasRespiratorias;
    }

    public void setDoencasRespiratorias(String doencasRespiratorias) {
        this.doencasRespiratorias = doencasRespiratorias;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
