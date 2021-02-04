package br.com.zup.ecommerce.domain.produto.detalhes;

import br.com.zup.ecommerce.domain.produto.Caracteristica;
import br.com.zup.ecommerce.domain.produto.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoDTO {

    private String nomeDoProduto;
    private BigDecimal preco;
    private Set<Caracteristica> caracteristicas;
    private String descricao;

    public ProdutoDTO(Produto produto) {
        this.nomeDoProduto = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

}
