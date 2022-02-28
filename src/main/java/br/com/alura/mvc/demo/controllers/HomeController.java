package br.com.alura.mvc.demo.controllers;

import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.orm.Produto;
import br.com.alura.mvc.demo.utils.status.SituacaoPedido;
import br.com.alura.mvc.demo.repository.PedidoRepository;
import br.com.alura.mvc.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public ModelAndView home(){
        Pageable pageable = PageRequest.of(0, 25, Sort.by(Sort.Direction.DESC, "dataDaEntrega"));
        List<Pedido> pedidos = pedidoRepository.findBySituacao(SituacaoPedido.ENTREGUE, pageable);

        ModelAndView mv = new ModelAndView("home");
        mv.addObject("pedidos", pedidos);

        return mv;
    }

    @GetMapping("/produtos")
    public ModelAndView produtos() {
        Pageable pageable = PageRequest.of(0, 25, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Produto> page = produtoRepository.findAll(pageable);
        List<Produto> produtos = page.stream().toList();

        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("produtos", produtos);
        return mv;
    }
}
