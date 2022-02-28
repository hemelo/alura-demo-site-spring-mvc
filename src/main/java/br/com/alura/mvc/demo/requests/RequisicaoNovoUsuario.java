package br.com.alura.mvc.demo.requests;

import br.com.alura.mvc.demo.orm.Role;
import br.com.alura.mvc.demo.orm.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequisicaoNovoUsuario implements Requisicao<User>{
    @Pattern(regexp="^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$")
    @NotEmpty
    @NotNull
    private String senha;

    @NotEmpty
    @NotNull
    private String username;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public User generate() {

        User user = new User();
        user.setUsername(this.getUsername());
        user.setPassword(this.getSenha());
        user.getRoles().add(new Role("ROLE_USER"));
        return user;
    }
}
