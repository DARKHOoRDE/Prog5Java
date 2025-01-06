package org.example.dma6m6beadando.Dto;

import lombok.Data;


public class AuthRespDTO {

    private String token;
    private String tokenType;


    public AuthRespDTO(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
