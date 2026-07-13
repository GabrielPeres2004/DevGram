package com.gabriel.devgram.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabriel.devgram.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class UserRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1534739145258562385L;


    @NotBlank(message = "O campo nome é requerido")
    protected String username;

    @NotBlank(message = "O campo e-mail é requerido")
    @Email
    protected String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "O campo senha é requerido")
    @Size(min = 6, message = "O minimo de caracteres é 6")
    protected String password;

    @NotBlank(message = "Informe o nome completo")
    protected String fullName;

    protected String bio;

    protected String imageURL;

    public UserRequestDTO() {
        super();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}