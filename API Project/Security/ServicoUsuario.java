public class ServicoUsuario {

    public Usuario registrarUsuario(DadosUsuario dadosUsuario) {
       
        return new Usuario();
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) {
        /
        return new Usuario();
    }

    public PerfilUsuario obterPerfilUsuario(String idUsuario) {
     
        return new PerfilUsuario();
    }

    public void atualizarPerfilUsuario(String idUsuario, PerfilUsuario perfil) {
      
    }

    public void alterarSenha(String idUsuario, String novaSenha) {
     
    }

    public void excluirUsuario(String idUsuario) {
    
    }
}
