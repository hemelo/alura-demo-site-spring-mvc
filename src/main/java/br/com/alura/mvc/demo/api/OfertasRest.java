package br.com.alura.mvc.demo.api;

import br.com.alura.mvc.demo.orm.Oferta;
import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.repository.PedidoRepository;
import br.com.alura.mvc.demo.requests.RequisicaoOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criaOferta(@Valid @RequestBody RequisicaoOferta requisicao){
        Optional<Pedido> optPedido = pedidoRepository.findById(requisicao.getPedidoId());
        Pedido pedido = optPedido.get();

        Oferta oferta = requisicao.generate();
        oferta.setPedido(pedido);
        pedido.getOfertas().add(oferta);
        pedidoRepository.save(pedido);

        return oferta;
    }
}
