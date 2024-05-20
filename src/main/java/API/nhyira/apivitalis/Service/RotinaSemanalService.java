package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Entity.RotinaMensal;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.RotinaMensalRepository;
import API.nhyira.apivitalis.Repository.RotinaSemanalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaSemanalService {

    private final RotinaSemanalRepository semanalRepository;
    private final RotinaMensalRepository mensalRepository;

    public List<RotinaSemanal> showAll(int id){
        Optional<RotinaMensal> optMensal = mensalRepository.findById(id);
        optMensal.orElseThrow(() -> new NaoEncontradoException("Rotina mensal"));
        List<RotinaSemanal> optSemanal = semanalRepository.findByRotinaMensalIdIs(optMensal.get());
        optMensal.orElseThrow(() -> new NaoEncontradoException("Rotina semanal"));
        return optSemanal;
    }
}
