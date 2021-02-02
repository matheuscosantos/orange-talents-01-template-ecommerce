package br.com.zup.ecommerce.ecommerce.domain.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 6)
    private String senha;

    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();

    public Usuario(@NotBlank @NotNull @Email String login,
                   @NotBlank @NotNull @Size(min = 6) String senha) {
        Assert.isTrue(StringUtils.hasLength(login), "O login não pode estar em branco.");
        Assert.isTrue(StringUtils.hasLength(senha), "A senha não pode estar em branco.");
        Assert.isTrue(senha.length() >= 6, "A senha precisa ter no mínimo 6 caracteres.");
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
