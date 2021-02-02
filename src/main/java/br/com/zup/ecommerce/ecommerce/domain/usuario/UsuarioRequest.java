package br.com.zup.ecommerce.ecommerce.domain.usuario;

import br.com.zup.ecommerce.ecommerce.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank
    @NotNull
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
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
