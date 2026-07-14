package com.gabriel.devgram.dtos.response;

import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.enums.Visibility;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PostResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7030828192972814405L;

    protected Long id;

    protected String title;

    protected String content;

    protected String imageUrl;

    protected String location;

    protected Visibility visibility;

    protected Boolean edited = false;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected Long authorId;

    protected String authorUsername;

    protected String authorImageURL;

    protected Integer commentaryCount;

    protected Integer likeCount;

    public PostResponseDTO() {
        super();
    }

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.location = post.getLocation();
        this.visibility = post.getVisibility();
        this.edited = post.getEdited();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.authorId = post.getUser().getId();
        this.authorUsername = post.getUser().getUsername();
        this.authorImageURL = post.getUser().getImageURL();
        this.commentaryCount = post.getCommentary().size();
        this.likeCount = post.getLike().size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
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

    public Integer getCommentaryCount() {
        return commentaryCount;
    }

    public void setCommentaryCount(Integer commentaryCount) {
        this.commentaryCount = commentaryCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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
}
