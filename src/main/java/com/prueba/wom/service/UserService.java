package com.prueba.wom.service;

import com.prueba.wom.dto.response.LoginResponse;
import com.prueba.wom.exception.CustomException;
import com.prueba.wom.model.User;
import com.prueba.wom.repository.UserRepository;
import com.prueba.wom.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que proporciona operaciones relacionadas con los usuarios.
 * Encargado de la autenticación, generación de tokens y obtención de información de usuario.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor que inicializa el repositorio de usuarios, el proveedor de tokens y el gestor de autenticación.
     */
    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Inicia sesión y genera un token JWT para el usuario.
     */
    public LoginResponse signin(String username, String password) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = this.userRepository.findByUsername(username);

            if (user == null) {
                LOG.warn("User with username '{}' not found.", username);
                throw new CustomException("El nombre de usuario/contraseña proporcionados no son válidos.", HttpStatus.UNPROCESSABLE_ENTITY);
            }

            String name = user.getName() + " " + user.getLastname();
            return new LoginResponse(this.jwtTokenProvider.createToken(username, name));

        } catch (AuthenticationException e) {
            LOG.error("Authentication failed for user '{}'", username, e);
            throw new CustomException("El nombre de usuario/contraseña proporcionados no son válidos", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            LOG.error("Unexpected error during signin for user '{}'", username, e);
            throw new CustomException("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene el usuario actualmente logueado.
     */
    public User getCurrentlyLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return user;
    }
}
