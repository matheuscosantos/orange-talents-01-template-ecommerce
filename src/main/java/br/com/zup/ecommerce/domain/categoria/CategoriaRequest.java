package br.com.zup.ecommerce.domain.categoria;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.config.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaRequest {

    @NotBlank
    @NotNull
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    @JsonProperty
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @JsonProperty
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager em) {
        if(!(idCategoriaMae == null)){
            Categoria possivelCategoria= em.find(Categoria.class, this.idCategoriaMae);
            return new Categoria(this.nome, possivelCategoria);
        }
        return new Categoria(this.nome);
    }

}