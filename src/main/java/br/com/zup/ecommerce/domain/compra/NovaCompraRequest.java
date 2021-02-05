package br.com.zup.ecommerce.domain.compra;

import br.com.zup.ecommerce.config.validators.ExistsId;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NovaCompraRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayDePagamento gateway;

    @ManyToOne
    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    @Min(value = 1)
    private Long quantidade;

    public Compra toModel(EntityManager em, Usuario usuarioLogado) {
        Produto produto = em.find(Produto.class, idProduto);
        return new Compra(this.gateway, produto, this.quantidade, usuarioLogado);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayDePagamento getGateway() {
        return gateway;
    }

    public Long getQuantidade() {
        return quantidade;
    }
}
