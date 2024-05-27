package API.nhyira.apivitalis.Chat.Entity;

import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChat;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "personalId", nullable = false)
    private Usuario personalId;
}
