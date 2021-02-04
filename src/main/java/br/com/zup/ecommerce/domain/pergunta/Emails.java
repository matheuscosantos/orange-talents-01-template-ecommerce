package br.com.zup.ecommerce.domain.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    public void novaPergunta(@NotNull @Valid Pergunta pergunta){
        System.out.println(pergunta.toString());
    }
}
