package com.gabriel.devgram.dtos.request;

import com.gabriel.devgram.domain.enums.Visibility;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class PostUpdateRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8562498171092837465L;

    @Size(max = 120, message = "O título deve ter no máximo 120 caracteres")
    protected String title;

    @Size(max = 500, message = "O conteúdo deve ter no máximo 500 caracteres")
    protected String content;

    protected String imageUrl;

    @Size(max = 150, message = "A localização deve ter no máximo 150 caracteres")
    protected String location;

    protected Visibility visibility;

    public PostUpdateRequestDTO() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}