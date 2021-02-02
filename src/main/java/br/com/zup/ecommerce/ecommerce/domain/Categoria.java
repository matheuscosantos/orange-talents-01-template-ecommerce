package br.com.zup.ecommerce.ecommerce.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public Categoria(@NotBlank @NotNull String nome,
                     Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

}
