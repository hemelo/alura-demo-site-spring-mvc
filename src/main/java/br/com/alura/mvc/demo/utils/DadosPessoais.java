package br.com.alura.mvc.demo.utils;

import javax.persistence.*;

@Embeddable
public class DadosPessoais {
    private String cep;
    private String email;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
