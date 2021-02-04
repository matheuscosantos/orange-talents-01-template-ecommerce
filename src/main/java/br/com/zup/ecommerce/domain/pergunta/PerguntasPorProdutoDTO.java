package br.com.zup.ecommerce.domain.pergunta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PerguntasPorProdutoDTO {
    List<PerguntaDTO> dtos = new ArrayList<>();

    public PerguntasPorProdutoDTO(List<Pergunta> perguntasRecebidas) {
        dtos.addAll(perguntasRecebidas.stream().map(PerguntaDTO::new).collect(Collectors.toList()));
    }

    public List<PerguntaDTO> getDtos() {
        return dtos;
    }

}
