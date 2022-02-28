package br.com.alura.mvc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoPassword {
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String getEncoded(String senha) {
        return passwordEncoder.encode(senha);
    }
}
