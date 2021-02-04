package br.com.zup.ecommerce.domain.usuario;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioLogado {

    private final UsuarioRepository repository;

    public UsuarioLogado(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario retornaUsuarioLogado(){
        String emailDoUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuarioLogado = repository.findByLogin(emailDoUsuarioLogado);
        return usuarioLogado.get();
    }
}
