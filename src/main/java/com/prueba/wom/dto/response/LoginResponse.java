package com.prueba.wom.dto.response;

import com.prueba.wom.dto.BaseDTO;
/**
 * DTO que representa una respuesta de inicio de sesión.
 *
 * Esta clase se utiliza para transportar el token JWT generado tras un inicio
 * de sesión exitoso desde la capa de servicio hasta la capa de presentación.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
public class LoginResponse extends BaseDTO {

    private String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
