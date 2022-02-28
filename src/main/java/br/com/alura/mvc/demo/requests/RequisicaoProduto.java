package br.com.alura.mvc.demo.requests;

import br.com.alura.mvc.demo.orm.Produto;
import br.com.alura.mvc.demo.services.CryptoPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequisicaoProduto implements Requisicao<Produto> {

    @Pattern(regexp = "(http)?s?:?(\\/\\/[^\"']*\\.(?:png|jpg|jpeg|gif|png|svg))")
    @NotNull
    private String urlImage;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Produto generate() {
        Produto produto = new Produto();
        produto.setNome(this.getNome());
        produto.setDescricao(this.getDescricao());
        produto.setImage(this.getUrlImage());
        return produto;
    }
}
