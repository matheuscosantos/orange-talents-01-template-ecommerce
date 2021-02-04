package br.com.zup.ecommerce.domain.opiniao;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @JsonProperty
    private Integer nota;

    @NotBlank
    @NotNull
    @JsonProperty
    private String titulo;

    @NotBlank
    @NotNull
    @Size(max = 500)
    @JsonProperty
    private String descricao;

    public Opiniao toModel(EntityManager em, Usuario usuarioLogado, Produto produto){

        return new Opiniao(this.nota,
                           this.titulo,
                           this.descricao,
                           usuarioLogado,
                           produto);
    }

}
