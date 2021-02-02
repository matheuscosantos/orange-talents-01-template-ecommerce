package br.com.zup.ecommerce.ecommerce.domain.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank
    @NotNull
    @Email
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 6)
    private String senha;

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
