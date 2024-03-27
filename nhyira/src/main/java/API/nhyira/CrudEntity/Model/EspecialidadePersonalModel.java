package API.nhyira.CrudEntity.Model;

import java.time.LocalDate;


public class EspecialidadePersonalModel {

    private Integer idEspecialidadePersonal;
    private LocalDate tempoExperiencia;
    private PersonalModel personal;
    private EspecialidadeModel especialidade;

    public Integer getIdEspecialidadePersonal() {
        return idEspecialidadePersonal;
    }

    public void setIdEspecialidadePersonal(Integer idEspecialidadePersonal) {
        this.idEspecialidadePersonal = idEspecialidadePersonal;
    }

    public LocalDate getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(LocalDate tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public PersonalModel getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }

    public EspecialidadeModel getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeModel especialidade) {
        this.especialidade = especialidade;
    }
}
