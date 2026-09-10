package com.gabriel.devgram.services;

import com.gabriel.devgram.domain.Like;
import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.repositories.LikeRepository;
import com.gabriel.devgram.services.exceptions.DataIntegrityViolationException;
import com.gabriel.devgram.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;



    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Like findById(Long id) {
        Optional<Like> like = likeRepository.findById(id);

        return like.orElseThrow( () -> new ObjectNotFoundException("Like não encontrado") );
    }

    public List<Like> findByPostId(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    public List<Like> findByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    public Like create(Long postId) {
        Post post = postService.findById(postId);
        User user = userService.findById(1L);

        if (likeRepository.existsByPostIdAndUserId(postId, user.getId())) {
            throw new DataIntegrityViolationException("Você já curtiu este post.");
        }

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);

        return likeRepository.save(like);
    }

    @Transactional
    public void delete(Long postId) {
        postService.findById(postId);
        Long userId = 1L;
        likeRepository.deleteByPostIdAndUserId(postId, userId);
    }
}
