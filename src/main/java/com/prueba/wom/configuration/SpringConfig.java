package com.prueba.wom.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * SpringConfig proporciona beans de configuración para la aplicación.
 * <p>
 * Esta clase de configuración contiene beans que son esenciales para el
 * correcto funcionamiento de la aplicación, como ModelMapper para
 * mapeo de objeto a objeto y RestTemplate para realizar peticiones HTTP.
 * </p>
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Configuration
@ComponentScan(basePackages = {"com.prueba.wom"})
@EnableConfigurationProperties
public class SpringConfig {

    /**
     * Bean para ModelMapper que proporciona un fácil mapeo de objetos.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * RestTemplate proporciona métodos para comunicarse con el servidor HTTP
     * a través de GET, POST, PUT, DELETE y otros métodos HTTP.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
