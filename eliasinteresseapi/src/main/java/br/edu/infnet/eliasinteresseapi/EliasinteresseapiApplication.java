package br.edu.infnet.eliasinteresseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EliasinteresseapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EliasinteresseapiApplication.class, args);
    }

}
