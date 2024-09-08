package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.Chat.DTO.ChatCreateEditDto;
import API.nhyira.apivitalis.Chat.Service.ChatService;
import API.nhyira.apivitalis.DTO.Contrato.ContratoCreateEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoEditDto;
import API.nhyira.apivitalis.DTO.Contrato.ContratoMapper;
import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.ErroClienteTipoException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.ContratoRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final ChatService chatService;

    public Contrato create(Contrato contrato, int id, int idPersonal){


        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        optUsuario.orElseThrow(() -> new NaoEncontradoException("Usuario"));
        if (optUsuario.get().getTipo().equals("PERSONAL"))throw new ErroClienteTipoException("USUARIO");


        Optional<Usuario> optPersonal = usuarioRepository.findById(idPersonal);
        optPersonal.orElseThrow(() -> new NaoEncontradoException("Personal"));
        if (optPersonal.get().getTipo().equals("USUARIO"))throw new ErroClienteTipoException("PERSONAL");

        contrato.setUsuarioId(optUsuario.get());
        contrato.setPersonalId(optPersonal.get());
        contrato.setAfiliacao(0);


        Contrato contratoSalvo = contratoRepository.save(contrato);
        return contratoSalvo;
    }

    public Contrato show(int id){
        Optional<Contrato> optContrato = contratoRepository.findById(id);
        optContrato.orElseThrow(() -> new NaoEncontradoException("Contrato"));
        return optContrato.get();
    }

    public List<Contrato> shows(int id){
        Optional<Usuario> optPersonal = usuarioRepository.findById(id);
        optPersonal.orElseThrow(() -> new NaoEncontradoException("Personal"));
        List<Contrato> contratos = contratoRepository.buscarContratoPorPersonal(optPersonal.get());
        return contratos;
    }

    public Contrato updtUser(int id, ContratoEditDto contrato){
        ChatCreateEditDto chatCreateEditDto = new ChatCreateEditDto();

        Optional<Contrato> optContrato = contratoRepository.findById(id);
        optContrato.orElseThrow(() -> new NaoEncontradoException("Contrato"));
        Contrato contratoUpd = ContratoMapper.toEdit(optContrato.get(), contrato);
        contratoUpd.setAfiliacao(contrato.getAfiliado());
        contratoRepository.save(contratoUpd);
        usuarioService.afiliacao(contratoUpd.getUsuarioId(), contratoUpd.getPersonalId());

        chatCreateEditDto.setAtivo(false);
        chatService.saveChat(chatCreateEditDto ,optContrato.get().getUsuarioId(), optContrato.get().getPersonalId());

        return optContrato.get();
    }

    public boolean del(int id){
        if (!contratoRepository.existsById(id)){
            throw new NaoEncontradoException("Id");
        }
        contratoRepository.deleteById(id);
        return true;
    }


}
