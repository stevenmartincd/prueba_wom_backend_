package com.prueba.wom.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configurador que integra el filtro de token JWT en el flujo de seguridad de Spring.
 * Esta clase se encarga de configurar y agregar el filtro de token JWT justo antes
 * del filtro de autenticación de nombre de usuario y contraseña  en la cadena de filtros de seguridad.
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructor que inicializa el proveedor de token JWT.
     */
    public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Configura el filtro de token JWT para que se ejecute antes del filtro de autenticación estándar.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
