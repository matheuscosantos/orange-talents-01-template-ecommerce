package br.com.zup.ecommerce.ecommerce.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid UsuarioRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UsuarioResponse usuarioSalvo = new UsuarioResponse(repository.save(request.toModel()));
        return ResponseEntity.ok(usuarioSalvo);
    }
}
