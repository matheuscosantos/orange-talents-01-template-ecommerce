package br.com.zup.ecommerce.domain.produto.detalhes;

import br.com.zup.ecommerce.domain.opiniao.Opiniao;
import br.com.zup.ecommerce.domain.opiniao.OpiniaoRepository;
import br.com.zup.ecommerce.domain.pergunta.Pergunta;
import br.com.zup.ecommerce.domain.pergunta.PerguntaRepository;
import br.com.zup.ecommerce.domain.produto.Imagem;
import br.com.zup.ecommerce.domain.produto.ImagemRepository;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/produtos")
public class DetalhesController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @GetMapping(value = "/{idProduto}/detalhes")
    public ResponseEntity<?> detalhaProduto(@PathVariable @Valid Long idProduto){
        Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
        List<Imagem> possiveisImagens = imagemRepository.findAllByProdutoId(idProduto);
        List<Pergunta> possiveisPerguntas = perguntaRepository.findAllByProdutoId(idProduto);
        List<Opiniao> possiveisOpinioes = opiniaoRepository.findAllByProdutoId(idProduto);
        if(possivelProduto.isPresent()){
            DetalhesDTO detalhes = new DetalhesDTO(possivelProduto.get(),
                    possiveisImagens,
                    possiveisOpinioes,
                    possiveisPerguntas);
            return ResponseEntity.ok(detalhes);
        }
        return ResponseEntity.notFound().build();
    }
}
