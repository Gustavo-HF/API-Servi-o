package com.servicos.servico.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Serviços",
                version = "1.0",
                description = "Documentação dos endpoints de serviços usando Springdoc OpenAPI.",
                contact = @Contact(name = "Equipe de API", email = "suporte@servico.com")
        )
)
public class OpenApiConfig {
}
