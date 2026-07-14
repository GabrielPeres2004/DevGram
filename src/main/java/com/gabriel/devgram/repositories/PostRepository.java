package com.gabriel.devgram.repositories;

import com.gabriel.devgram.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
