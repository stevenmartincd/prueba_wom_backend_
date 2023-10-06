package com.prueba.wom.dto.response;

import com.prueba.wom.dto.BaseDTO;

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
