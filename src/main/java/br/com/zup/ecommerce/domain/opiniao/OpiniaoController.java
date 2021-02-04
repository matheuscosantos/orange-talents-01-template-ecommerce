package br.com.zup.ecommerce.domain.opiniao;

import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import br.com.zup.ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/produtos")
public class OpiniaoController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "/{idProduto}/opiniao")
    @Transactional
    public ResponseEntity cria(@PathVariable Long idProduto, @RequestBody @Valid OpiniaoRequest request){

        String emailDoUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuarioLogado = usuarioRepository.findByLogin(emailDoUsuarioLogado);

        Produto possivelProduto = em.find(Produto.class, idProduto);
        if(possivelProduto != null){
            em.persist(request.toModel(em, usuarioLogado.get(), possivelProduto));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
