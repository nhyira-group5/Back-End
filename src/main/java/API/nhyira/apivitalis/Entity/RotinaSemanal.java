package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Getter
@Setter
@Entity
public class RotinaSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaSemanal;


    @ManyToOne
    @JoinColumn(name = "rotinaMensalId")
    private RotinaMensal rotinaMensalId;

    private TinyIntJdbcType concluida;
}
