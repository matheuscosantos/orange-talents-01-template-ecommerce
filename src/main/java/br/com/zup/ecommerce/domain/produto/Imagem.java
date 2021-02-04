package br.com.zup.ecommerce.domain.produto;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    private String link;

    @Deprecated
    public Imagem() {
    }

    public Imagem(@NotNull Produto produto,
                  @NotNull String link) {

        Assert.isTrue(produto != null, "O produto é obrigatório.");
        Assert.isTrue(link != null, "O link é obrigatório.");

        this.produto = produto;
        this.link = link;
    }
}
