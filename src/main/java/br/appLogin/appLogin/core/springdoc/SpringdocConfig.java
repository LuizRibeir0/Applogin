package br.appLogin.appLogin.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public OpenAPI openAPI (){
        return new OpenAPI()
                .info(new Info()
                        .title("Teste API AppLogin")
                        .version("1.0")
                        .description("Testando a documentação da API com swagger")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Link do repositorio deste projeto")
                        .url("https://github.com/LuizRibeir0/Applogin")
                );
    }

}
