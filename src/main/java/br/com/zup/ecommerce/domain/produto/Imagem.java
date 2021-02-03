package br.com.zup.ecommerce.domain.produto;

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
        this.produto = produto;
        this.link = link;
    }
}
