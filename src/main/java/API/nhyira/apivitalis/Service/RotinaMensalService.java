package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import API.nhyira.apivitalis.Repository.RotinaUsuarioRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaMensalService {
    private final RotinaMensalRepository rotinaMensalRepository;
    private final RotinaUsuarioRepository rotinaUsuarioRepository;
    private final RotinaUsuarioService usuarioService;

    public RotinaMensal show(int id, int mes){
        Optional<RotinaUsuario> optRotinaUsuairo = rotinaUsuarioRepository.findById(id);
        optRotinaUsuairo.orElseThrow(() -> new NaoEncontradoException("Rotina usuario"));
        RotinaMensal rotinaMensal = rotinaMensalRepository.buscarMes(optRotinaUsuairo.get(), mes);
        if (rotinaMensal == null)throw new NaoEncontradoException("Rotina do mes");
        return rotinaMensal;
    }

    public RotinaMensal showById(int id){
        RotinaMensal rotinaMensal = rotinaMensalRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Rotina Mensal"));
        return rotinaMensal;
    }

    public RotinaMensal showPorUsuario(int id, int mes){
        RotinaUsuario rotinaUsuario = usuarioService.show(id);
        RotinaMensal rotinaMensal = rotinaMensalRepository.buscarMes(rotinaUsuario, mes);
        if (rotinaMensal == null)throw new NaoEncontradoException("Rotina do mes");
        return rotinaMensal;
    }

    public RotinaMensal updateConcluido (int id, int concluido) {
        RotinaMensal rm = rotinaMensalRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Rotina Mensal"));
        if (rm.getConcluido() == concluido) return rm;
        rm.setConcluido(concluido);
        return rotinaMensalRepository.save(rm);
    }
}
