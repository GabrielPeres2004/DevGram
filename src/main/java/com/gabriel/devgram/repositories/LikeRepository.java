package com.gabriel.devgram.repositories;

import com.gabriel.devgram.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostIdAndUserId(Long postId, Long userId);
    void deleteByPostIdAndUserId(Long postId, Long userId);
    List<Like> findByPostId(Long postId);
    List<Like> findByUserId(Long userId);


}
