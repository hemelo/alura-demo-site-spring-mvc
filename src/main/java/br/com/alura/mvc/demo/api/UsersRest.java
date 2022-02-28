package br.com.alura.mvc.demo.api;

import br.com.alura.mvc.demo.orm.User;
import br.com.alura.mvc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsersRest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> aguardandoOfertas(){
        return userRepository.findAll();
    }

}
