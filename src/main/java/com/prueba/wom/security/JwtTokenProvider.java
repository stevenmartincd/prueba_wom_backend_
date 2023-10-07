package com.prueba.wom.security;

import com.prueba.wom.exception.CustomException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Proveedor para la creación y validación de tokens JWT.
 * Esta clase se encarga de la gestión de tokens JWT.
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secretkey}")
    private String secretKey;

    @Value("${security.jwt.token.expirelength}")
    private long validityInMilliseconds;

    private final MyUserDetails myUserDetails;

    /**
     * Constructor que inicializa los detalles del usuario.
     */
    @Autowired
    public JwtTokenProvider(MyUserDetails myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    /**
     * Inicializa y codifica la clave secreta para su uso.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Crea un token JWT para el usuario especificado.
     */
    public String createToken(String username, String name) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("name", name);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Obtiene la autenticación a partir del token JWT.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Extrae el nombre de usuario desde el token JWT.
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Resuelve el token a partir de la solicitud HTTP.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Valida el token JWT.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Token JWT caducado o no es válido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
