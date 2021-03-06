package br.com.zup.ecommerce.domain.produto;

import br.com.zup.ecommerce.domain.usuario.Usuario;
import br.com.zup.ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/api/produtos")
@Transactional
public class ImagemController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{idProduto}/imagens")
    public ResponseEntity adicionaImagemAoProduto(@PathVariable("idProduto") Long idProduto,
                                                  @RequestParam MultipartFile imagem,
                                                  @AuthenticationPrincipal Usuario usuarioLogado){

        Produto possivelProduto = em.find(Produto.class, idProduto);

        if(possivelProduto != null) {
            if (usuarioLogado.getId() == possivelProduto.getUsuarioCriador().getId()) {
                String linkFicticio = "/link/imagens/.../" + imagem.getOriginalFilename();
                em.persist(new Imagem(possivelProduto, linkFicticio));
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.notFound().build();
    }
}
