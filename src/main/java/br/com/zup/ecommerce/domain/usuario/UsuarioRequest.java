package br.com.zup.ecommerce.domain.usuario;

import br.com.zup.ecommerce.config.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank
    @NotNull
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    @JsonProperty
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 6)
    @JsonProperty
    private String senha;

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }
}
