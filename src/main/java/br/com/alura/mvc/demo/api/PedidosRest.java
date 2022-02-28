package br.com.alura.mvc.demo.api;

import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.utils.status.SituacaoPedido;
import br.com.alura.mvc.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    public List<Pedido> aguardandoOfertas(){
        Pageable pageable = PageRequest.of(0, 25, Sort.by(Sort.Direction.DESC, "id"));
        return pedidoRepository.findBySituacao(SituacaoPedido.AGUARDANDO, pageable);
    }

    @GetMapping("aprovados")
    public List<Pedido> aprovados(){
        Pageable pageable = PageRequest.of(0, 25, Sort.by(Sort.Direction.DESC, "id"));
        return pedidoRepository.findBySituacao(SituacaoPedido.APROVADO, pageable);
    }

    @GetMapping("entregues")
    public List<Pedido> entregues(){
        Pageable pageable = PageRequest.of(0, 25, Sort.by(Sort.Direction.DESC, "id"));
        return pedidoRepository.findBySituacao(SituacaoPedido.ENTREGUE, pageable);
    }
}
