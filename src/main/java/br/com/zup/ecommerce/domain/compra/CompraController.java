package br.com.zup.ecommerce.domain.compra;

import br.com.zup.ecommerce.domain.email.Emails;
import br.com.zup.ecommerce.domain.produto.Produto;
import br.com.zup.ecommerce.domain.usuario.Usuario;
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
public class CompraController {

    @PersistenceContext
    EntityManager em;

    @PostMapping(value = "/compra")
    @Transactional
    public String cria(@AuthenticationPrincipal Usuario usuarioLogado,
                             @RequestBody @Valid NovaCompraRequest request){

        Compra compra = request.toModel(em, usuarioLogado);

        Produto produto = em.find(Produto.class, request.getIdProduto());
        if(produto.abateQuantidadeDaCompraNoEstoque(request.getQuantidade())){

            em.persist(compra);

            NovaCompraResponse novaCompra = new NovaCompraResponse(compra);

            new Emails().novaCompra(novaCompra);

            if(request.getGateway().equals(GatewayDePagamento.PAYPAL)){
                return "paypal.com/"+ novaCompra.getId() + "?redirectUrl={urlRetornoAppPosPagamento}";
            }else if(request.getGateway().equals(GatewayDePagamento.PAGSEGURO)){
                return "pagseguro.com/"+ novaCompra.getId() + "?redirectUrl={urlRetornoAppPosPagamento}";
            }
            return "Teste";
        }
        return "A quantidade solicitada é maior que a quantidade em estoque";
    }
}
