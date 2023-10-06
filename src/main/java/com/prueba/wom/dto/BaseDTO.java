package com.prueba.wom.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

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