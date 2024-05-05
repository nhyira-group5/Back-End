package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.Entity.Usuario;

public class FichaExibitionDto {


    private Integer id;


    private Integer bebe;

    private Integer fuma;
    private String deficiencias;
    private String problemasCaridiacos;
    private String doencasRespiratorias;
    private Float peso;
    private float altura;
    private String meta;

    private Usuario usuarioId;

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


    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
