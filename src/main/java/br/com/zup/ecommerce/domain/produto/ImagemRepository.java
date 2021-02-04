package br.com.zup.ecommerce.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findAllByProdutoId(Long idProduto);
}
