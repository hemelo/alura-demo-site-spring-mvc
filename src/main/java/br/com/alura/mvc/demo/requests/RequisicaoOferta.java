package br.com.alura.mvc.demo.requests;

import br.com.alura.mvc.demo.orm.Oferta;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequisicaoOferta implements Requisicao<Oferta>{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NotNull
    private Long pedidoId;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotNull
    private String valor;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}")
    @NotNull
    private String dataDaEntrega;

    private String comentario;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getValor() {
        return new BigDecimal(this.valor);
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public LocalDate getDataDaEntrega() {
        return LocalDate.parse(this.dataDaEntrega, formatter);
    }

    public void setDataDaEntrega(String dataDaEntrega) {
        this.dataDaEntrega = dataDaEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public Oferta generate() {
        Oferta oferta = new Oferta();
        oferta.setComentario(this.getComentario());
        oferta.setValor(this.getValor());
        oferta.setDataDaEntrega(this.getDataDaEntrega());

        return oferta;
    }
}
