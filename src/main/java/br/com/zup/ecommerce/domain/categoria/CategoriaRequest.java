package br.com.zup.ecommerce.domain.categoria;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.config.validators.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaRequest {

    @NotBlank
    @NotNull
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager em) {
        if(!(idCategoriaMae == null)){
            Categoria possivelCategoria= em.find(Categoria.class, this.idCategoriaMae);
            return new Categoria(this.nome, possivelCategoria);
        }
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

}
