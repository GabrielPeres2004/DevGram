package com.gabriel.devgram.repositories;

import com.gabriel.devgram.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> findByPostId(Long postId);
    List<Commentary> findByUserId(Long userId);

}
