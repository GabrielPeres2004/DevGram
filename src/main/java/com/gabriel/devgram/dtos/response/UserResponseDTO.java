package com.gabriel.devgram.dtos.response;

import com.gabriel.devgram.domain.Commentary;
import com.gabriel.devgram.domain.Like;
import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.domain.enums.Role;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7356519037285045175L;


    protected Long id;

    protected String username;

    protected String email;

    protected String fullName;

    protected String bio;

    protected Role role = Role.USER;

    protected LocalDateTime createdAt = LocalDateTime.now();

    protected LocalDateTime updatedAt = LocalDateTime.now();

    protected Integer postCount;

    protected Integer commentaryCount;

    protected Integer likeCount;

    public UserResponseDTO() {
        super();
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.bio = user.getBio();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        postCount = user.getPosts().size();
        commentaryCount = user.getCommentary().size();
        likeCount = user.getLikes().size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
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
}
