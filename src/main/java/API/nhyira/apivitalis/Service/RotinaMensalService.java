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
    public RotinaMensal show(int id, String mes){
        Optional<RotinaUsuario> optRotinaUsuairo = rotinaUsuarioRepository.findById(id);
        optRotinaUsuairo.orElseThrow(() -> new NaoEncontradoException("Rotina usuario"));
        RotinaMensal rotinaMensal = rotinaMensalRepository.buscarMes(optRotinaUsuairo.get(), mes);
        if (rotinaMensal == null)throw new NaoEncontradoException("Rotina do mes");
        return rotinaMensal;
    }


}
