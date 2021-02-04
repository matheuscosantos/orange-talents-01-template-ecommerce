package br.com.zup.ecommerce.domain.pergunta;

import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @NotNull
    private String titulo;

    @CreationTimestamp
    @NotNull
    private LocalDateTime instanteDeCriacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank @NotNull String titulo,
                    @NotNull Usuario usuario,
                    @NotNull Produto produto) {

        Assert.isTrue(titulo != null || titulo.trim().equals(""), "O título é obrigatório");
        Assert.isTrue(usuario != null, "O usuário é obrigatório");
        Assert.isTrue(produto != null, "O produto é obrigatório");

        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteDeCriacao() {
        return instanteDeCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo='" + titulo + '\'' +
                ", instanteDeCriacao=" + instanteDeCriacao +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }
}