package com.prueba.wom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
/**
 * Configuración de Swagger para la API de PRUEBA WOM.
 * <p>
 * Esta clase se encarga de configurar Swagger, una herramienta que permite
 * documentar y probar de forma interactiva los endpoints de la API.
 * </p>
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Configura la documentación de Swagger para la API.
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.prueba.wom"))
                .paths(PathSelectors.any()).build().useDefaultResponseMessages(false)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }
    /**
     * Configura los controladores de recursos para Swagger UI.
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    /**
     * Define la clave API para la autenticación.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API PRUEBA WOM")
                .description("Interfaz de servicios entre el Backend y el FrontEnd (UI)")
                .contact(new Contact("Steven Cuevas", "", "stevenmartincd@gmail.com")).build();
    }
    /**
     * Configura el contexto de seguridad para Swagger.
     */
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }
    /**
     * Proporciona la configuración de autenticación por defecto.
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }

}
