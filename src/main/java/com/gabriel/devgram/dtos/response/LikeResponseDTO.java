package com.gabriel.devgram.dtos.response;

import com.gabriel.devgram.domain.Like;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class LikeResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1571727608038549333L;

    protected Long id;

    protected Long authorId;

    protected String authorUsername;

    protected String authorImageURL;

    protected Long postId;

    protected LocalDateTime createdAt;

    public LikeResponseDTO() {
        super();
    }

    public LikeResponseDTO(Like like) {
        this.id = like.getId();
        this.authorId = like.getUser().getId();
        this.authorUsername = like.getUser().getUsername();
        this.authorImageURL = like.getUser().getImageURL();
        this.postId = like.getPost().getId();
        this.createdAt = like.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
