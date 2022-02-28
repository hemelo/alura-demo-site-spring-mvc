package br.com.alura.mvc.demo.controllers;

import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.orm.Produto;
import br.com.alura.mvc.demo.orm.User;
import br.com.alura.mvc.demo.repository.PedidoRepository;
import br.com.alura.mvc.demo.repository.ProdutoRepository;
import br.com.alura.mvc.demo.repository.UserRepository;
import br.com.alura.mvc.demo.requests.RequisicaoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("pedido/formulario")
    public ModelAndView formulario(RequisicaoPedido requisicaoPedido){
        ModelAndView mv = new ModelAndView("formularioPedido");
        mv.addObject("produtos", produtoRepository.findAll());
        return mv;
    }

    @PostMapping("pedido/formulario")
    public ModelAndView novo(@Valid RequisicaoPedido novo, BindingResult result){
        if(result.hasErrors()){
            return this.formulario(novo);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Optional<Produto> optProduto = produtoRepository.findById(novo.getProduto());
        Produto produto = optProduto.get();

        Pedido pedido = novo.generate();
        pedido.setProduto(produto);
        pedido.setUser(user);
        pedidoRepository.save(pedido);
        return new ModelAndView("redirect:/");
    }
}
