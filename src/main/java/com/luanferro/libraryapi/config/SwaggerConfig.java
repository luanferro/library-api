package com.luanferro.libraryapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("LIbrary API")
                .description("API do Projeto de controle de aluguel de livros")
                .version("1.0")
                .contact(contact());
    }

    private Contact contact() {
       Contact contact = new Contact();
       contact.setEmail("luanferror@gmail.com");
       contact.setName("Luan Ferro");
       contact.setUrl("http://github.com/luanferro");

       return contact;
    }
}

