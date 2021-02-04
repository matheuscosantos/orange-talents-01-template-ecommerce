package br.com.zup.ecommerce.domain.produto.detalhes;

import br.com.zup.ecommerce.domain.opiniao.Opiniao;

public class OpiniaoDTO {
    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private String usuario;

    public OpiniaoDTO(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = opiniao.getUsuario().getUsername();
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }
}