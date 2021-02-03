package br.com.zup.ecommerce.domain.produto;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.domain.categoria.Categoria;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.context.SecurityContextHolder;

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
    @JsonProperty
    private String nome;

    @Min(value = 0)
    @JsonProperty
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    @JsonProperty
    private Integer quantidadeDisponivel;

    @Size(min = 3, max = 20)
    @JsonProperty
    private Set<Caracteristica> caracteristicas = new HashSet<Caracteristica>();

    @NotBlank
    @Size(max = 1000)
    @JsonProperty
    private String descricao;

    @NotNull
    @ManyToOne
    @JsonProperty
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public Produto toModel(EntityManager em, Usuario usuario){

        Categoria categoriaEncontrada = em.find(Categoria.class, idCategoria);

        return new Produto(nome,
                           valor,
                           quantidadeDisponivel,
                           caracteristicas,
                           descricao,
                           categoriaEncontrada,
                           usuario
                );
    }
}
