package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication@OpenAPIDefinition(
        info = @Info(
                title = "Moja API Dokumentacija",
                version = "1.0",
                description = "Opis API-ja"
        )
)
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args); //pokrece i automatski konfigurise spring boot kontejner
    }
}