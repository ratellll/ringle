package com.example.ringle.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ringleOpenAPI() {
        Info info = new Info()
                .title("Ringle API")
                .description("Ringle API 명세서입니다.")
                .version("v1.0")
                .contact(new Contact()
                        .name("최현빈")
                        .email("latellbin@naver.com")
                        .url("https://github.com/ratellll")
                );

        ExternalDocumentation externalDocumentation = new ExternalDocumentation()
                .description("깃허브 주소")
                .url("https://github.com/ratellll/ringle");

        return new OpenAPI()
                .info(info)
                .externalDocs(externalDocumentation);
    }
}
