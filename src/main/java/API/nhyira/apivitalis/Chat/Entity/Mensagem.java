package API.nhyira.apivitalis.Chat.Entity;

import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "mensagem")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMensagem;

    private String assunto;
    private LocalDate dataHora;

    @ManyToOne
    @JoinColumn(name = "chatId", nullable = false)
    private Chat chatId;

    @ManyToOne
    @JoinColumn(name = "remetenteId", nullable = false)
    private Usuario remetenteId;

    @ManyToOne
    @JoinColumn(name = "destinatarioId", nullable = false)
    private Usuario destinatarioId;
}
