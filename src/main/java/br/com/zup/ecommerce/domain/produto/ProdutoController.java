package br.com.zup.ecommerce.domain.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity cria(@RequestBody @Valid ProdutoRequest request){
        em.persist(request.toModel(em));
        return ResponseEntity.ok().build();
    }
}
