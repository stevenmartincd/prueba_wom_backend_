package com.prueba.wom.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
/**
 * Clase base DTO
 * Esta clase representa un objeto base de transferencia de datos
 * Las clases DTO específicas pueden extender de esta clase para heredar estos atributos.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
public class BaseDTO implements Serializable {

    protected long id;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}