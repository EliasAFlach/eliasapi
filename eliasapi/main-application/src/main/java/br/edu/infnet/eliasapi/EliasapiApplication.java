package br.edu.infnet.eliasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EliasapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EliasapiApplication.class, args);
    }
}
