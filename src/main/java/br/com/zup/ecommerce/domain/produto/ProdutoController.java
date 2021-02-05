package br.com.zup.ecommerce.domain.produto;

import br.com.zup.ecommerce.domain.usuario.Usuario;
import br.com.zup.ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private EntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cria(@RequestBody @Valid ProdutoRequest request,
                               @AuthenticationPrincipal Usuario usuarioLogado){
        em.persist(request.toModel(em, usuarioLogado));
        return ResponseEntity.ok().build();
    }
}
