package com.gabriel.devgram.repositories;

import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.enums.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByContentContainingIgnoreCase(String content);

    List<Post> findByUserId(Long userId);

    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Post> findByVisibility(Visibility visibility);

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findByCreatedAtAfter(LocalDateTime data);

    List<Post> findByLocationContainingIgnoreCase(String location);

    long countByUserId(Long userId);
}