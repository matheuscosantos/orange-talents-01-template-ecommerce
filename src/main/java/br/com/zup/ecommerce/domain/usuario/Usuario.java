package br.com.zup.ecommerce.domain.usuario;

import br.com.zup.ecommerce.domain.autenticacao.Perfil;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {
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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

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

//    public String getSenha() {
//        return senha;
//    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return login.equals(usuario.login) && senha.equals(usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, senha);
    }
}
