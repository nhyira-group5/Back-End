package API.nhyira;

public class Agendamento {
    private String usuario;
    private String personal;
    private String horario;

    public Agendamento(String usuario, String personal, String horario) {
        this.usuario = usuario;
        this.personal = personal;
        this.horario = horario;
    }

    // Getters e Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
