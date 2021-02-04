package br.com.zup.ecommerce.domain.pergunta;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    @NotNull
    @JsonProperty
    private String titulo;

    public Pergunta toModel(Usuario usuarioLogado, Produto produto) {
        return new Pergunta(titulo, usuarioLogado, produto);
    }
}
