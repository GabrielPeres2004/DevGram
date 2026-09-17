package com.gabriel.devgram.services;

import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.domain.enums.Visibility;
import com.gabriel.devgram.dtos.request.PostRequestDTO;
import com.gabriel.devgram.dtos.request.PostUpdateRequestDTO;
import com.gabriel.devgram.repositories.PostRepository;
import com.gabriel.devgram.security.UserSS;
import com.gabriel.devgram.services.exceptions.DataIntegrityViolationException;
import com.gabriel.devgram.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado com o id: " + id));
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Post> findByContent(String content) {
        return postRepository.findByContentContainingIgnoreCase(content);
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public List<Post> findByUserIdOrderByCreatedAtDesc(Long userId) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Post> findByVisibility(Visibility visibility) {
        return postRepository.findByVisibility(visibility);
    }

    public List<Post> findAllOrderByCreatedAtDesc() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> findByCreatedAtAfter(LocalDateTime data) {
        return postRepository.findByCreatedAtAfter(data);
    }

    public List<Post> findByLocation(String location) {
        return postRepository.findByLocationContainingIgnoreCase(location);
    }

    public long countByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }

    public Post create(PostRequestDTO postRequestDTO) {
        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();
        User user = userService.findById(userId);

        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setImageUrl(postRequestDTO.getImageUrl());
        post.setLocation(postRequestDTO.getLocation());
        post.setVisibility(postRequestDTO.getVisibility() != null ? postRequestDTO.getVisibility() : Visibility.PUBLIC);
        post.setEdited(false);
        post.setUser(user);

        return postRepository.save(post);
    }

    public Post update(Long id, PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = findById(id);
        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();

        if (!post.getUser().getId().equals(userId)) {
            throw new DataIntegrityViolationException("Você não tem permissão para editar este post.");
        }

        post.setTitle(postUpdateRequestDTO.getTitle() != null ? postUpdateRequestDTO.getTitle() : post.getTitle());
        post.setContent(postUpdateRequestDTO.getContent() != null ? postUpdateRequestDTO.getContent() : post.getContent());
        post.setImageUrl(postUpdateRequestDTO.getImageUrl() != null ? postUpdateRequestDTO.getImageUrl() : post.getImageUrl());
        post.setLocation(postUpdateRequestDTO.getLocation() != null ? postUpdateRequestDTO.getLocation() : post.getLocation());
        post.setVisibility(postUpdateRequestDTO.getVisibility() != null ? postUpdateRequestDTO.getVisibility() : post.getVisibility());
        post.setEdited(true);
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public void delete(Long id) {
        Post post = findById(id);
        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();

        if (!post.getUser().getId().equals(userId)) {
            throw new DataIntegrityViolationException("Você não tem permissão para deletar este post.");
        }

        postRepository.delete(post);
    }

}