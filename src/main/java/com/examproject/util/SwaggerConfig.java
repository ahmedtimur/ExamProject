package com.examproject.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Token",apiKeySecurityScheme()))
                .info(new Info().title("Exam App").description("Written by me"))
                .security(List.of(new SecurityRequirement().addList("Bearer Token")));
    }

    private SecurityScheme apiKeySecurityScheme() {
        return new SecurityScheme()
                .name("Authorization")
                .description("paste jwt token here")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }

}
