package com.prueba.wom.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * DTO que representa una solicitud de inicio de sesión.
 *
 * Esta clase se utiliza para capturar y transportar los datos de inicio de sesión
 * del usuario desde la capa de presentación hasta la capa de servicio.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
public class LoginRequest implements Serializable {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}