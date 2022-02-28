package br.com.alura.mvc.demo.controllers;

import br.com.alura.mvc.demo.orm.Produto;
import br.com.alura.mvc.demo.repository.ProdutoRepository;
import br.com.alura.mvc.demo.repository.UserRepository;
import br.com.alura.mvc.demo.requests.RequisicaoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public ModelAndView formulario(RequisicaoProduto requisicaoPedido){
        return new ModelAndView("formularioProduto");
    }

    @PostMapping("formulario")
    public String novo(@Valid RequisicaoProduto novo, BindingResult result){
        if(result.hasErrors()){
            return "formularioProduto";
        }
        Produto produto = novo.generate();
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

}
