package br.com.alura.mvc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @GetMapping
    public ModelAndView formularioOfertas() {
        return new ModelAndView("oferta");
    }
}
