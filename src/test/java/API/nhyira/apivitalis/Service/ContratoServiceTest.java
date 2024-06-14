package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Chat.DTO.ChatCreateEditDto;
import API.nhyira.apivitalis.Chat.Entity.Chat;
import API.nhyira.apivitalis.Chat.Service.ChatService;
import API.nhyira.apivitalis.DTO.Contrato.ContratoEditDto;
import API.nhyira.apivitalis.Entity.Contrato;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.ContratoRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("\uD83C\uDF9F\uFE0F - Testes de contratoService")
class ContratoServiceTest {


    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository  usuarioRepository;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ContratoService contratoService;

    @Test
    @DisplayName("Dado que, vou salvar, salve no banco e retorne o objeto com id e entidade do usuario/personal")
    void cenarioSaveCorreto(){
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        LocalDate dataNascPersoanl = LocalDate.parse("1999-09-14");

        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null, null);
        Usuario personal = new Usuario(2, "w1llSalad","23456789012", "Will Dantas", dataNascPersoanl, "M", "will@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.PERSONAL, null, null, null);
        ChatCreateEditDto chat = new ChatCreateEditDto();

        LocalDate dataInicio = LocalDate.parse("2024-08-13");


        Contrato contratoParaSalvar = new Contrato(null, null, null, dataInicio , null, 0);
        Contrato contratoSalvo = new Contrato(1, usuario, personal, dataInicio , null, 0);

        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findById(personal.getIdUsuario())).thenReturn(Optional.of(personal));
        contratoParaSalvar.setUsuarioId(usuario);
        contratoParaSalvar.setPersonalId(personal);
        when(contratoRepository.save(contratoParaSalvar)).thenReturn(contratoSalvo);


        chat.setAtivo(false);
        Contrato contrato = contratoService.create(contratoParaSalvar, usuario.getIdUsuario(), personal.getIdUsuario());
        Chat chatCriar = chatService.saveChat(chat, usuario, personal);
        assertNotNull(contrato.getIdContrato());
        assertEquals(contratoParaSalvar.getUsuarioId(), contrato.getUsuarioId());
        assertEquals(contratoParaSalvar.getPersonalId(), contrato.getPersonalId());


        verify(contratoRepository, times(1)).save(contratoParaSalvar);
        verify(usuarioRepository, times(1)).findById(usuario.getIdUsuario());
        verify(usuarioRepository, times(1)).findById(personal.getIdUsuario());
    }


    @Test
    @DisplayName("Dado que passei um id valido, salve a afiliacao salva e retorne ele com o valor")
    void cenarioAtualizarCorreto(){
        int idInformado = 1;
        Usuario usuario = new Usuario();
        Usuario personal = new Usuario();

        LocalDate dataInicio = LocalDate.parse("2024-08-13");
        ContratoEditDto contrato = new ContratoEditDto(null, 1);
        Contrato contratoAtualizada = new Contrato(1, usuario, personal, dataInicio , null, 0);
        Contrato contratoAtualizacao = new Contrato(1, usuario, personal, dataInicio , null, 1);

        when(contratoRepository.findById(idInformado)).thenReturn(Optional.of(contratoAtualizada));
        contratoAtualizada.setAfiliacao(contrato.getAfiliado());
        when(contratoRepository.save(contratoAtualizada)).thenReturn(contratoAtualizacao);

        Contrato contratoResposta = contratoService.updtUser(idInformado, contrato);
        usuarioService.afiliacao(contratoAtualizada.getUsuarioId(), contratoAtualizada.getPersonalId());
        assertNotNull(contratoResposta.getIdContrato());
        assertEquals(contratoResposta.getAfiliacao(), contratoAtualizacao.getAfiliacao());

        verify(contratoRepository, times(1)).findById(idInformado);
        verify(contratoRepository, times(0)).existsById(idInformado);
    }

    @Test
    @DisplayName("Dado que, vou buscar um contrato por id e retorne o objeto desejado")
    void cenarioBuscarPorId(){
        int idInformado = 1;
        Usuario usuario = new Usuario();
        Usuario personal = new Usuario();

        LocalDate dataInicio = LocalDate.parse("2024-08-13");
        Contrato contrato = new Contrato(1, usuario, personal, dataInicio , null, 0);

        when(contratoRepository.findById(idInformado)).thenReturn(Optional.of(contrato));

        Contrato contrato1 = contratoService.show(idInformado);

        assertEquals(idInformado, contrato1.getIdContrato());
        assertEquals(contrato.getInicioContrato(), contrato1.getInicioContrato());

        verify(contratoRepository, times(1)).findById(idInformado);
        verify(contratoRepository, times(0)).existsById(idInformado);

    }

    @Test
    @DisplayName("Dado que, vou deletar um contrato por id e retorne true")
    void cenarioDeletarPorId(){
        int idInformado = 1;

        when(contratoRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        boolean contrato = contratoService.del(idInformado);

        verify(contratoRepository, times(1)).existsById(idInformado);
        verify(contratoRepository, times(0)).findById(idInformado);
        verify(contratoRepository, times(0)).findAll();


    }


    @Test
    @DisplayName("Teste incorreto se, ao passar um id nao encontrou o contrato")
    void cenarioIncorretoPorId(){
        Integer idInformado = 1;

        when(contratoRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> contratoService.show(idInformado));

        verify(contratoRepository, times(1)).findById(idInformado);
        verify(contratoRepository, times(0)).findAll();
        verify(contratoRepository, times(0)).existsById(idInformado);
    }


    @Test
    @DisplayName("Teste incorreto se, ao passar um id nao existe o contrato")
    void cenarioIncorretoDeletar(){
        Integer idInformado = 1;


        when(contratoRepository.existsById(any())).thenReturn(Boolean.FALSE );

        assertThrows(NaoEncontradoException.class, () -> contratoService.del(idInformado));

        verify(contratoRepository, times(1)).existsById(idInformado);
        verify(contratoRepository, times(0)).findAll();
        verify(contratoRepository, times(0)).findById(idInformado);
    }

    @Test
    @DisplayName("Teste incorreto se, ao atualizar nao existe id")
    void cenarioIncorretoAtualizar(){
        Integer idInformado = 1;

        Usuario usuario = new Usuario();
        Usuario personal = new Usuario();

        LocalDate dataInicio = LocalDate.parse("2024-08-13");
        Contrato contrato = new Contrato(1, usuario, personal, dataInicio , null, 0);

        when(contratoRepository.findById(idInformado)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> contratoService.updtUser(idInformado, null));

        verify(contratoRepository, times(0)).existsById(idInformado);
        verify(contratoRepository, times(0)).findAll();
        verify(contratoRepository, times(1)).findById(idInformado);
    }

    @Test
    @DisplayName("Teste incorreto se, ao criar nao existir o usuario")
    void cenarioIncorretoCriar(){
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        LocalDate dataNascPersoanl = LocalDate.parse("1999-09-14");

        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null, null);
        Usuario personal = new Usuario(2, "w1llSalad","23456789012", "Will Dantas", dataNascPersoanl, "M", "will@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.PERSONAL, null, null, null);
        LocalDate dataInicio = LocalDate.parse("2024-08-13");

        Contrato contratoParaSalvar = new Contrato(null, usuario, personal, dataInicio , null, 0);
        Contrato contratoSalvo = new Contrato(1, usuario, personal, dataInicio , null, 0);


        assertThrows(NaoEncontradoException.class, () -> contratoService.create(contratoParaSalvar, usuario.getIdUsuario(), personal.getIdUsuario()));



        verify(contratoRepository, times(0)).existsById(contratoParaSalvar.getIdContrato());
        verify(contratoRepository, times(0)).findAll();
        verify(usuarioRepository, times(1)).findById(any());
        verify(usuarioRepository, times(1)).findById(any());
    }


}