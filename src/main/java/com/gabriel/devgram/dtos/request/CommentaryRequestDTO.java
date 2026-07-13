package com.gabriel.devgram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class CommentaryRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1807500226222369247L;

    @NotBlank(message = "O comentário não pode estar vazio")
    @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres")
    protected String content;

    public CommentaryRequestDTO() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}