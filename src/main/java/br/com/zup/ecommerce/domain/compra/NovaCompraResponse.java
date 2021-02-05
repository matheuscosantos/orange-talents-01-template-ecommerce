package br.com.zup.ecommerce.domain.compra;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaCompraResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String status;

    @JsonProperty
    private String gateway;

    @JsonProperty
    private Long idProduto;

    @JsonProperty
    private Long quantidade;

    @JsonProperty
    private String emailComprador;

    @JsonProperty
    private String dataDeCompra;

    public NovaCompraResponse(Compra compra) {
        this.id = compra.getId();
        this.status = compra.getStatus().toString();
        this.gateway = compra.getGateway().toString();
        this.idProduto = compra.getProduto().getId();
        this.quantidade = compra.getQuantidade();
        this.emailComprador = compra.getComprador().getUsername();
        this.dataDeCompra = compra.getDataDeCompra().toString();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NovaCompraResponse{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", gateway='" + gateway + '\'' +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", emailComprador='" + emailComprador + '\'' +
                ", dataDeCompra='" + dataDeCompra + '\'' +
                '}';
    }
}
