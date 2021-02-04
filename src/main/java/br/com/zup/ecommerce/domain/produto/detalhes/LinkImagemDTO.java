package br.com.zup.ecommerce.domain.produto.detalhes;

import br.com.zup.ecommerce.domain.produto.Imagem;

public class LinkImagemDTO {
    private Long idImagem;
    private String link;

    public LinkImagemDTO(Imagem imagem){
        this.idImagem = imagem.getId();
        this.link = imagem.getLink();
    }

    public Long getIdImagem() {
        return idImagem;
    }

    public String getLink() {
        return link;
    }
}
