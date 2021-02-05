package br.com.zup.ecommerce.domain.pergunta;

import br.com.zup.ecommerce.domain.email.Emails;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import br.com.zup.ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class PerguntaController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    private Emails email;

    @PostMapping(value = "/{idProduto}/pergunta")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable Long idProduto,
                               @RequestBody @Valid PerguntaRequest request,
                               @AuthenticationPrincipal Usuario usuarioLogado){

        Produto possivelProduto = em.find(Produto.class, idProduto);
        if(possivelProduto != null){
            Pergunta novaPergunta = request.toModel(usuarioLogado, possivelProduto);
            em.persist(novaPergunta);
            List<Pergunta> perguntas = perguntaRepository.findAllByProdutoId(idProduto);
            PerguntasPorProdutoDTO dtos = new PerguntasPorProdutoDTO(perguntas);

            RestTemplate restTemplate = new RestTemplate();

            new Emails().novaPergunta(novaPergunta);

            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.notFound().build();
    }
}
