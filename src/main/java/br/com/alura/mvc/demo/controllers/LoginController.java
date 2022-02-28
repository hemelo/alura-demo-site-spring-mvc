package br.com.alura.mvc.demo.controllers;

import br.com.alura.mvc.demo.orm.User;
import br.com.alura.mvc.demo.repository.UserRepository;
import br.com.alura.mvc.demo.requests.RequisicaoNovoUsuario;
import br.com.alura.mvc.demo.services.CryptoPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/registrar")
    public String registrar(RequisicaoNovoUsuario requisicaoNovoUsuario){
        return "registrar";
    }

    @PostMapping("/registrar")
    public String processarRegistro(@Valid RequisicaoNovoUsuario novo, BindingResult result) {
        if(!result.hasErrors()) {
            Optional<User> optUser = userRepository.findById(novo.getUsername());
            if (optUser.isEmpty()) {
                User user = novo.generate();
                user.setPassword(CryptoPassword.getEncoded(user.getPassword()));
                userRepository.save(user);
                return "redirect:home";
            } else {
                result.rejectValue("username", null, "Já tem uma conta registrada com esse usuário");
                return "registrar";
            }
        }
        return "registrar";
    }
}
