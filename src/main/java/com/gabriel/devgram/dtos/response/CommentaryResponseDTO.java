package com.gabriel.devgram.dtos.response;

import com.gabriel.devgram.domain.Commentary;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentaryResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4004119592104587979L;

    protected Long id;

    protected String content;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected Long authorId;

    protected String authorUsername;

    protected String authorImageURL;

    protected Long postId;

    public CommentaryResponseDTO() {
        super();
    }

    public CommentaryResponseDTO(Commentary commentary) {
        this.id = commentary.getId();
        this.content = commentary.getContent();
        this.createdAt = commentary.getCreatedAt();
        this.updatedAt = commentary.getUpdatedAt();
        this.authorId = commentary.getUser().getId();
        this.authorUsername = commentary.getUser().getUsername();
        this.authorImageURL = commentary.getUser().getImageURL();
        this.postId = commentary.getPost().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorImageURL() {
        return authorImageURL;
    }

    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
