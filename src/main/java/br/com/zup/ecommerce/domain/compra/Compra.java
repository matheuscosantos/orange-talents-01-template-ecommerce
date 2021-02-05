package br.com.zup.ecommerce.domain.compra;

import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.INICIADA;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayDePagamento gateway;

    @ManyToOne
    @NotNull
    private Produto produto;

    @NotNull
    @Min(value = 1)
    private Long quantidade;

    @NotNull
    @ManyToOne
    private Usuario comprador;

    @CreationTimestamp
    private LocalDateTime dataDeCompra = LocalDateTime.now();

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull GatewayDePagamento gateway,
                  @NotNull Produto produto,
                  @NotNull @Min(value = 1) Long quantidade,
                  @NotNull Usuario comprador) {
        Assert.isTrue(gateway != null, "O Gateway é obrigatório.");
        Assert.isTrue(produto != null, "O Produto é obrigatório.");
        Assert.isTrue(quantidade != null, "A quantidade é obrigatória.");
        Assert.isTrue(quantidade >= 1, "A quantidade deve ser maior ou igual a 1.");
        Assert.isTrue(comprador != null, "O Gateway é obrigatório.");

        this.gateway = gateway;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public GatewayDePagamento getGateway() {
        return gateway;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public LocalDateTime getDataDeCompra() {
        return dataDeCompra;
    }
}

