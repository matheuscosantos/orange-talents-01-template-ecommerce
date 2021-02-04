package br.com.zup.ecommerce.domain.pergunta;

public class PerguntaDTO {
    private Long idProduto;
    private String titulo;

    public PerguntaDTO(Pergunta pergunta) {
        this.idProduto = pergunta.getId();
        this.titulo = pergunta.getTitulo();
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getTitulo() {
        return titulo;
    }

}
