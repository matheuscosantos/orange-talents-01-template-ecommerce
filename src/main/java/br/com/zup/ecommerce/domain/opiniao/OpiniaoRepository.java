package br.com.zup.ecommerce.domain.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
    List<Opiniao> findAllByProdutoId(Long idProduto);
}
