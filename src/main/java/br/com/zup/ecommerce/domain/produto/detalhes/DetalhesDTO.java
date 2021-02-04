package br.com.zup.ecommerce.domain.produto.detalhes;

import br.com.zup.ecommerce.domain.opiniao.Opiniao;
import br.com.zup.ecommerce.domain.pergunta.Pergunta;
import br.com.zup.ecommerce.domain.pergunta.PerguntaDTO;
import br.com.zup.ecommerce.domain.produto.Imagem;
import br.com.zup.ecommerce.domain.produto.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DetalhesDTO {
    private ProdutoDTO produtoDTO;
    private List<LinkImagemDTO> linksDTO = new ArrayList<>();
    private List<OpiniaoDTO> opinioesDTO = new ArrayList<>();
    private List<PerguntaDTO> perguntasDTO = new ArrayList<>();
    private OptionalDouble mediaDasNotas;
    private long totalDeNotas;


    public DetalhesDTO(Produto produto,
                       List<Imagem> imagens,
                       List<Opiniao> opinioes,
                       List<Pergunta> perguntas) {
        this.produtoDTO = new ProdutoDTO(produto);

        linksDTO.addAll(imagens.stream().map(LinkImagemDTO::new).collect(Collectors.toList()));
        opinioesDTO.addAll(opinioes.stream().map(OpiniaoDTO::new).collect(Collectors.toList()));
        perguntasDTO.addAll(perguntas.stream().map(PerguntaDTO::new).collect(Collectors.toList()));
        IntStream mapToInt = opinioes.stream().mapToInt(Opiniao::getNota);
        mediaDasNotas = mapToInt.average();
        if(mediaDasNotas.isEmpty()){
            mediaDasNotas = OptionalDouble.of(0);
        }

        mapToInt = opinioes.stream().mapToInt(Opiniao::getNota);
        totalDeNotas = mapToInt.count();
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public List<LinkImagemDTO> getLinksDTO() {
        return linksDTO;
    }

    public List<OpiniaoDTO> getOpinioesDTO() {
        return opinioesDTO;
    }

    public List<PerguntaDTO> getPerguntasDTO() {
        return perguntasDTO;
    }

    public OptionalDouble getMediaDasNotas() {
        return mediaDasNotas;
    }

    public long getTotalDeNotas() {
        return totalDeNotas;
    }
}