import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @Test
    public void testObterUsuarioPorId() {
        UsuarioService usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioService(usuarioService);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.obterUsuarioPorId(1L)).thenReturn(new ResponseEntity<>(usuario, HttpStatus.OK));

        ResponseEntity<Usuario> response = usuarioController.obterUsuarioPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testListarUsuarios() {
        UsuarioService usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioService(usuarioService);

        Page<Usuario> usuarios = mock(Page.class);
        when(usuarios.getContent()).thenReturn(new ArrayList<>());
        when(usuarioService.listarUsuariosPaginados(any(Pageable.class))).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.listarUsuarios(0, 10, new String[]{"id"}, "asc");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCadastrarUsuario() {
        UsuarioService usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioService(usuarioService);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.cadastrarUsuario(usuario)).thenReturn(new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.CREATED));

        ResponseEntity<String> response = usuarioController.cadastrarUsuario(usuario);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuário cadastrado com sucesso", response.getBody());
    }

    @Test
    public void testAtualizarUsuario() {
        UsuarioService usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioService(usuarioService);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.atualizarUsuario(1L, usuario)).thenReturn(new ResponseEntity<>("Usuário atualizado com sucesso", HttpStatus.OK));

        ResponseEntity<String> response = usuarioController.atualizarUsuario(1L, usuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário atualizado com sucesso", response.getBody());
    }

    @Test
    public void testDeletarUsuario() {
        UsuarioService usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioService(usuarioService);

        when(usuarioService.deletarUsuario(1L)).thenReturn(new ResponseEntity<>("Usuário excluído com sucesso", HttpStatus.OK));

        ResponseEntity<String> response = usuarioController.deletarUsuario(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário excluído com sucesso", response.getBody());
    }
}
