package br.com.alura.mvc.demo.requests;

import br.com.alura.mvc.demo.orm.Pedido;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequisicaoPedido implements Requisicao<Pedido>{
    @NotNull
    private Long produto;

    @NotNull
    private int quantidade;

    @NotNull
    @Pattern(regexp= "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
    private String email;

    @Pattern(regexp="\\d{5}-\\d{3}")
    private String cep;

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public Pedido generate() {

        Pedido pedido = new Pedido();
        pedido.setQuantidade(this.getQuantidade());
        pedido.setEmailCliente(this.getEmail());
        pedido.setEnderecoCliente(this.getCep());
        return pedido;
    }
}
