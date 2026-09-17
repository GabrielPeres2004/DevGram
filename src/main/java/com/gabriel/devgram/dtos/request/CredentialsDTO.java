package com.gabriel.devgram.dtos.request;

import java.io.Serial;
import java.io.Serializable;

public class CredentialsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8546626001102634735L;

    protected String email;
    protected String password;

    public CredentialsDTO() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}