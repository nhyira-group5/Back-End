package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Ficha.FichaCreateEditDto;
import API.nhyira.apivitalis.DTO.Ficha.FichaMapper;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.FichaRepository;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("\uD83C\uDF9F\uFE0F - Testes de contratoService")
class FichaServiceTest {


    @InjectMocks
    private FichaService fichaService;

    @Mock
    private FichaRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;


    // CREATE
    @Test
    @DisplayName("Dado que, vou salvar no banco, retorne a entidade com o id e a entidade do usuario")
    void cenarioCorretoSalvar(){
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        Ficha fichaParaSalvar = new Ficha(null, 1, 1, 0, 1,0,1,1.7f, 90.0f, null);

        Ficha fichaSalva = new Ficha(1, 1, 1, 0, 1,0,1,1.7f, 90.0f, usuario);

        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
        fichaParaSalvar.setUsuarioId(usuario);
        when(repository.save(fichaParaSalvar)).thenReturn(fichaSalva);

        Ficha ficha = fichaService.create(fichaParaSalvar, usuario.getIdUsuario());

        assertNotNull(ficha.getIdFicha());
        assertEquals(ficha.getUsuarioId(), fichaParaSalvar.getUsuarioId());
        assertEquals(ficha.getPeso(), fichaParaSalvar.getPeso());
        assertEquals(ficha.getAltura(), fichaParaSalvar.getAltura());

        verify(usuarioRepository, times(1)).findById(usuario.getIdUsuario());
        verify(repository, times(1)).save(fichaParaSalvar);
    }

    @Test
    @DisplayName("Dado que, passei um usuario na hora de salvar e ele nao encontrou")
    void cenarioErradoBuscarUsuarioParaSalvarFicha(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
      Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

     Ficha fichaParaSalvar = new Ficha(null, 1, 1, 0, 1,0,1,1.7f, 90.0f, null);

        when(usuarioRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> fichaService.create(fichaParaSalvar ,idUsuario));

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(usuarioRepository, times(0)).existsById(idUsuario);
        verify(usuarioRepository, times(0)).findAll();

    }


    // BUSCA POR ID
    @Test
    @DisplayName("Dado que, busco uma ficha pelo id do usuario, retorne a entidade")
    void cenarioCorretoBuscarPorId(){
        int idUsuario = 1;

        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        Ficha fichaSalva = new Ficha(1, 1, 1, 0, 1,0,1,1.7f, 90.0f, usuario);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.of(fichaSalva));

        Ficha ficha = fichaService.showFicha(idUsuario);

        assertNotNull(ficha.getIdFicha());
        assertEquals(usuario, ficha.getUsuarioId());

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(usuarioRepository, times(0)).existsById(idUsuario);
        verify(usuarioRepository, times(0)).findAll();

        verify(repository, times(0)).findById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);

    }

    @Test
    @DisplayName("Dado que, busco uma ficha pelo id do usuario, retorne a entidade")
    void cenarioErradoBuscarPorId(){
        int idUsuario = 1;
        when(usuarioRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> fichaService.showFicha(idUsuario));
        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(repository, times(0)).existsById(idUsuario);
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Dado que, busco uma ficha pelo id do usuario, retorne a entidade")
    void cenarioErradoBuscarPorIdFicha(){

        int idUsuario =1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> fichaService.showFicha(idUsuario));
        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);
        verify(repository, times(0)).findById(usuario.getIdUsuario());
    }

    //UPDATE
    @Test
    @DisplayName("Dado que passe o id valido, vou atualizar a ficha, retorne a entidade atualizada")
    void cenarioCorretoAtualizar(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        FichaCreateEditDto fichaEdit = new FichaCreateEditDto( 1, 1, 1, 1,0,0,1.7f, 83.1f, 1);

        Ficha fichaAtualizar = new Ficha(1, 1, 1, 1, 1,0,0,1.7f, 83.1f, usuario);

        Ficha fichaAtualizada = new Ficha( 1,1, 1, 1, 1,0,0,1.7f, 83.1f, usuario);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.of(fichaAtualizar));
        Ficha dto = FichaMapper.toEdit(fichaAtualizar, fichaEdit);
        when(repository.save(dto)).thenReturn(fichaAtualizada);

        Ficha ficha = fichaService.updtFicha(idUsuario, fichaEdit);

        assertNotNull(ficha.getIdFicha());
        assertEquals(ficha.getUsuarioId(), fichaAtualizar.getUsuarioId());

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);
        verify(repository, times(1)).save(dto);
        verify(repository, times(0)).findById(usuario.getIdUsuario());
    }


    @Test
    @DisplayName("Dado que o id invalido, retorne nao encontrado usuario")
    void cenarioErradoAtualizarBuscarUsuario(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        FichaCreateEditDto fichaEdit = new FichaCreateEditDto( 1, 1, 1, 1,0,0,1.7f, 83.1f, 1);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> fichaService.updtFicha(idUsuario, fichaEdit));

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(usuarioRepository, times(0)).existsById(idUsuario);
        verify(usuarioRepository, times(0)).findAll();

    }

    @Test
    @DisplayName("Dado que, busco uma ficha pelo usuario, nao existir a ficha no banco")
    void cenarioErradoAtualizarBuscarPorFicha(){

        int idUsuario =1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        FichaCreateEditDto fichaEdit = new FichaCreateEditDto( 1, 1, 1, 1,0,0,1.7f, 83.1f, 1);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> fichaService.updtFicha(idUsuario, fichaEdit));
        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);
        verify(repository, times(0)).findById(usuario.getIdUsuario());
    }



    //DELETE
    @Test
    @DisplayName("Dado que, vou deletar um ficha retorne verdadeira")
    void cenarioCorretoDeletar(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        Ficha fichaBanco = new Ficha(1, 1, 1, 0, 1,0,1,1.7f, 90.0f, usuario);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.of(fichaBanco));
        doNothing().when(repository).delete(fichaBanco);

        Boolean ficha = fichaService.delUser(idUsuario);

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);
        verify(repository, times(1)).delete(fichaBanco);
    }


    @Test
    @DisplayName("Dado que o id invalido, busco usuario, nao existir no banco")
    void cenarioErradoDeletarIdUsuario(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        Ficha ficha = new Ficha( 1,1, 1, 1, 1,0,0,1.7f, 83.1f, usuario);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> fichaService.delUser(idUsuario));

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(usuarioRepository, times(0)).existsById(idUsuario);
        verify(usuarioRepository, times(0)).findAll();

    }


    @Test
    @DisplayName("Dado que, busco uma ficha pelo usuario, nao existir a ficha no banco")
    void cenarioErradoDeletarFicha(){
        int idUsuario = 1;
        LocalDate dataNascUsuario = LocalDate.parse("2001-09-14");
        Usuario usuario = new Usuario(1, "marCOSSilV4","44581975840", "Marcos Silva Oliveira Pinto Santos", dataNascUsuario, "M", "marcos@example.com", "$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS", Usuario.TipoUsuario.USUARIO, null, null);

        Ficha ficha = new Ficha( 1,1, 1, 1, 1,0,0,1.7f, 83.1f, usuario);

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(repository.findByUsuarioIdIs(usuario)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> fichaService.delUser(idUsuario));

        verify(usuarioRepository, times(1)).findById(idUsuario);
        verify(usuarioRepository, times(0)).existsById(idUsuario);
        verify(repository, times(1)).findByUsuarioIdIs(usuario);
        verify(usuarioRepository, times(0)).findAll();

    }





}