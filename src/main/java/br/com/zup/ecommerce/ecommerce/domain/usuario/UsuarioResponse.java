package br.com.zup.ecommerce.ecommerce.domain.usuario;

public class UsuarioResponse {
    private String login;

    public UsuarioResponse(Usuario usuario) {
        this.login = usuario.getLogin();
    }

    public String getLogin() {
        return login;
    }




}
