package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.RotinaDiario;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaSemanalRepository;
import API.nhyira.apivitalis.Repository.RotinaDiarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaDiarioService {


    private final RotinaDiarioRepository diarioRepository;

    private final RotinaSemanalRepository semanalRepository;

    public RotinaDiario show(int id) {
        Optional<RotinaSemanal> optRotinaSemanal = semanalRepository.findById(id);
        optRotinaSemanal.orElseThrow(() -> new NaoEncontradoException("Rotina Semanal"));
        Optional<RotinaDiario> rotinaDiario = diarioRepository.findByRotinaSemanalIdIs(optRotinaSemanal.get());
        rotinaDiario.orElseThrow(() -> new NaoEncontradoException("Rotina Diario"));
        return rotinaDiario.get();
    }

}
