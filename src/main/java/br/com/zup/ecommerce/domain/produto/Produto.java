package br.com.zup.ecommerce.domain.produto;

import br.com.zup.ecommerce.domain.categoria.Categoria;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @Min(value = 0)
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Size(min = 3, max = 20)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas = new HashSet<Caracteristica>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @CreationTimestamp
    private LocalDateTime instanteDoCadastro = LocalDateTime.now();

    public Produto(@NotBlank @NotNull String nome,
                   @Min(value = 0) BigDecimal valor,
                   @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                   @Size(min = 3, max = 20) Set<Caracteristica> caracteristicas,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull Categoria categoria) {

        Assert.isTrue(StringUtils.hasLength(nome), "O nome do produto é obrigatório.");
        Assert.isTrue(valor != null, "O valor do produto deve ser maior que R$00,00");
        Assert.isTrue(quantidadeDisponivel != null, "A quantidade do produto é obrigatório.");
        Assert.isTrue(quantidadeDisponivel >= 0, "A quantidade do produto deve ser maior que 0.");
        Assert.isTrue(caracteristicas.size() >= 3, "O produto deve ter 3 ou mais características.");
        Assert.isTrue(StringUtils.hasLength(nome), "O produto deve pertencer a uma categoria.");

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteDoCadastro() {
        return instanteDoCadastro;
    }
}
