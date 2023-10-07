package com.prueba.wom.security;

import com.prueba.wom.model.User;
import com.prueba.wom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para recuperar detalles de un usuario para propósitos de autenticación y autorización.
 * Este servicio se encarga de recuperar los detalles de un usuario
 * a partir del nombre de usuario y luego lo convierte a un objeto UserDetails
 * compatible con Spring Security.
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor que inicializa el repositorio de usuarios.
     */
    @Autowired
    public MyUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga un usuario a partir del nombre de usuario y lo convierte a un objeto UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        String role = user.getRole();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
