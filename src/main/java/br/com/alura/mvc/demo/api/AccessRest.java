package br.com.alura.mvc.demo.api;

import br.com.alura.mvc.demo.interceptor.AccessInterceptor;
import br.com.alura.mvc.demo.interceptor.AccessInterceptor.Acesso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/acessos")
@RestController
public class AccessRest {

    @GetMapping
    public List<Acesso> acessos(){
        return AccessInterceptor.acessos;
    }
}
