package br.com.alura.mvc.demo.controllers;

import br.com.alura.mvc.demo.orm.Oferta;
import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.repository.ProdutoRepository;
import br.com.alura.mvc.demo.utils.status.SituacaoOferta;
import br.com.alura.mvc.demo.utils.status.SituacaoPedido;
import br.com.alura.mvc.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("pedidos")
    public ModelAndView pedidos(Principal principal){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataDaEntrega"));
        List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName(), pageable);

        ModelAndView mv = new ModelAndView("pedidos");
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("pedidos/{status}")
    public ModelAndView status(@PathVariable("status") String status, Principal principal){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataDaEntrega"));
        List<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(SituacaoPedido.valueOf(status.toUpperCase()), principal.getName(), pageable);

        ModelAndView mv = new ModelAndView("pedidos");
        mv.addObject("pedidos", pedidos);
        mv.addObject("status", status.toUpperCase());
        return mv;
    }

    @GetMapping("pedido/{pedido}")
    public ModelAndView pedido(@PathVariable("pedido") int pedidoId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Pedido pedido = pedidoRepository.findByIdAndUser((long) pedidoId, username);

        if(pedido == null){
            throw new IllegalArgumentException();
        }

        ModelAndView mv = new ModelAndView("pedido");
        mv.addObject("pedido", pedido);
        return mv;
    }

    @PostMapping("pedido/{pedido}/aprovar/{oferta}")
    public String aprovar(@PathVariable("pedido") Long pedidoId, @PathVariable("oferta") int ofertaId){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Pedido pedido = pedidoRepository.findByIdAndUser(pedidoId, username);

            if(pedido == null){
                throw new IllegalArgumentException();
            }

            pedido.getOfertas().forEach((Oferta offer) -> {
                if(offer.getId() == ofertaId) {
                    offer.setSituacao(SituacaoOferta.ACEITA);
                    pedido.setValorNegociado(offer.getValor());
                    pedido.setDataDaEntrega(offer.getDataDaEntrega());
                }else{
                    offer.setSituacao(SituacaoOferta.RECUSADA);
                }
            });

            pedido.aprovar();
            pedidoRepository.save(pedido);
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException | NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
        return "redirect:/usuario/pedidos";
    }

    @PostMapping("pedido/{pedido}/recusar/{oferta}")
    public String recusar(@PathVariable("pedido") Long pedidoId, @PathVariable("oferta") int ofertaId){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Pedido pedido = pedidoRepository.findByIdAndUser(pedidoId, username);

            if(pedido == null){
                throw new IllegalArgumentException();
            }

            pedido.getOfertas().forEach((Oferta offer) -> {
                if(offer.getId() == ofertaId) {
                    offer.setSituacao(SituacaoOferta.RECUSADA);
                }
            });

            pedidoRepository.save(pedido);
        }
        catch (IllegalArgumentException | IndexOutOfBoundsException | NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
        return "redirect:/usuario/pedidos";
    }

    @PostMapping("pedido/{pedido}/finalizar")
    public String finalizar(@PathVariable("pedido") Long pedidoId){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Pedido pedido = pedidoRepository.findByIdAndUser(pedidoId, username);

            if(pedido == null){
                throw new IllegalArgumentException();
            }

            pedido.finalizar();
            pedidoRepository.save(pedido);
        }
        catch (IllegalArgumentException | NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
        return "redirect:/usuario/pedidos";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/";
    }
}
