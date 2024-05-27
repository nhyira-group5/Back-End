package API.nhyira.apivitalis.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Metrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetrica;
    private String metrica;
}
