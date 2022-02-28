package br.com.alura.mvc.demo.orm;

import br.com.alura.mvc.demo.utils.status.SituacaoOferta;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private LocalDate dataDaEntrega;
    private String comentario;

    @Enumerated(EnumType.STRING)
    private SituacaoOferta situacao;

    @ManyToOne
    private Pedido pedido;

    public Oferta() {
        situacao = SituacaoOferta.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataDaEntrega() {
        return dataDaEntrega;
    }

    public void setDataDaEntrega(LocalDate dataDaEntrega) {
        this.dataDaEntrega = dataDaEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public SituacaoOferta getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOferta situacao) {
        this.situacao = situacao;
    }
}
