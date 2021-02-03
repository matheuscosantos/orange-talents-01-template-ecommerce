package br.com.zup.ecommerce.domain.produto;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.domain.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequest {
    @NotBlank
    @NotNull
    private String nome;

    @Min(value = 0)
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Size(min = 3, max = 20)
    private Set<Caracteristica> caracteristicas = new HashSet<Caracteristica>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public Produto toModel(EntityManager em){

        Categoria categoriaEncontrada = em.find(Categoria.class, idCategoria);

        return new Produto(nome,
                           valor,
                           quantidadeDisponivel,
                           caracteristicas,
                           descricao,
                           categoriaEncontrada
                );
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
