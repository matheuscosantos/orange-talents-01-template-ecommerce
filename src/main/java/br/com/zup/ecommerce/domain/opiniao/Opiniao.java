package br.com.zup.ecommerce.domain.opiniao;

import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer nota;

    @NotBlank
    @NotNull
    private String titulo;

    @NotBlank
    @NotNull
    @Size(max = 500)
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@NotNull @Size(min = 1, max = 5) Integer nota,
                   @NotBlank @NotNull String titulo,
                   @NotBlank @NotNull @Size(max = 500) String descricao,
                   Usuario usuario,
                   Produto produto) {

        Assert.isTrue(nota != null , "A nota é obrigatória.");
        Assert.isTrue(nota >= 1 && nota <=5 , "A nota deve estar entre 1 e 5.");
        Assert.isTrue(titulo != null, "O título é obrigatório.");
        Assert.isTrue(descricao != null, "A descrição é obrigatória");
        Assert.isTrue(descricao.length() <= 500, "A descrição deve ter até 500 caracteres.");

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}