package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Treino;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaDiariaRepository;
import API.nhyira.apivitalis.Repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaDiariaService {
    private final RotinaDiariaRepository diarioRepository;
    private final RotinaSemanalService semanalService;
    private final UsuarioService usuarioService;
    private final TreinoRepository tRep;

    public RotinaDiaria show(int id) {
        RotinaDiaria rotinaDiaria = diarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Rotina Diaria"));
        return rotinaDiaria;
    }

    public RotinaDiaria showCurrentDailyRoutine(int idRotinaUsuario) {
        RotinaSemanal rs = semanalService.show(idRotinaUsuario);
        int diaSemana = LocalDate.now().getDayOfWeek().getValue();
        diaSemana = (diaSemana % 7) + 1;    // Ajustando para começar no domingo
        return diarioRepository.showCurrentDailyRoutine(rs.getIdRotinaSemanal(), diaSemana);
    }

    public List<RotinaDiaria> showPorSemanal(int id) {
        RotinaSemanal rotinaSemanal = semanalService.show(id);
        List<RotinaDiaria> rotinaDiaria = diarioRepository.findByRotinaSemanalIdIs(rotinaSemanal);
        return rotinaDiaria;
    }

    public Integer showQtdExercicios (RotinaDiaria rd) {
        List<Treino> treinos = tRep.findByRotinaDiariaIdIs(rd);
        Integer qtdExercicios = treinos.size();
        return qtdExercicios;
    }

    public Integer showQtdExerciciosFeitos (RotinaDiaria rd) {
        Integer qtdExercicios = 0;
        List<Treino> treinos = tRep.findByRotinaDiariaIdIs(rd);
        for (Treino t : treinos) {
            if (t.getConcluido() == 1) {
                qtdExercicios++;
            }
        }
        return qtdExercicios;
    }

    public RotinaDiaria updateConcluido (int id, int concluido) {
        RotinaDiaria rd = diarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Rotina Diária"));
        if (rd.getConcluido() == concluido) return rd;
        rd.setConcluido(concluido);
        return diarioRepository.save(rd);
    }

    public RotinaDiaria rotinaPorUsuario(int id){
        Usuario usuario = usuarioService.showUserById(id);
        RotinaDiaria rotinaDiaria = diarioRepository.findFirstByRotinaDiaria(id);
        return rotinaDiaria;
    }
}
