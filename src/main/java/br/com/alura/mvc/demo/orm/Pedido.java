package br.com.alura.mvc.demo.orm;

import br.com.alura.mvc.demo.utils.status.SituacaoPedido;
import br.com.alura.mvc.demo.utils.DadosPessoais;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pedido")
    @JsonIgnore
    private List<Oferta> ofertas;

    private BigDecimal valorNegociado;
    private int quantidade;
    private LocalDate dataDaEntrega;

    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao;

    @Embedded
    private DadosPessoais cliente;

    public Pedido() {
        situacao = SituacaoPedido.AGUARDANDO;
        cliente = new DadosPessoais();
        ofertas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDaEntrega() {
        return dataDaEntrega;
    }

    public void setDataDaEntrega(LocalDate dataDaEntrega) {
        this.dataDaEntrega = dataDaEntrega;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public DadosPessoais getCliente() {
        return cliente;
    }

    public void setEmailCliente(String email) {
        this.cliente.setEmail(email);
    }

    public void setEnderecoCliente(String cep) {
        this.cliente.setCep(cep);
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

    public void aprovar() {
        this.situacao = SituacaoPedido.APROVADO;
    }


    public void finalizar() {
        this.situacao = SituacaoPedido.ENTREGUE;
    }

    public boolean isFinalizado() { return situacao == SituacaoPedido.ENTREGUE; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public BigDecimal getValorNegociado() {
        return valorNegociado;
    }

    public void setValorNegociado(BigDecimal valorNegociado) {
        this.valorNegociado = valorNegociado;
    }
}
