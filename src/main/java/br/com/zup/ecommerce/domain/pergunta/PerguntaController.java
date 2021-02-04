package br.com.zup.ecommerce.domain.pergunta;

import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
import br.com.zup.ecommerce.domain.usuario.UsuarioLogado;
import br.com.zup.ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class PerguntaController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @PostMapping(value = "/{idProduto}/pergunta")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable Long idProduto,
                               @RequestBody @Valid PerguntaRequest request){

        Usuario usuarioLogado = new UsuarioLogado(usuarioRepository).retornaUsuarioLogado();

        Produto possivelProduto = em.find(Produto.class, idProduto);
        if(possivelProduto != null){
            em.persist(request.toModel(usuarioLogado, possivelProduto));
            List<Pergunta> perguntas = perguntaRepository.findAllByProdutoId(idProduto);
            PerguntasPorProdutoDTO dtos = new PerguntasPorProdutoDTO(perguntas);
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.notFound().build();
    }
}
