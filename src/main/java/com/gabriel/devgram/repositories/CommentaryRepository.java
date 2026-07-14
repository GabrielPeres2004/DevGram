package com.gabriel.devgram.repositories;

import com.gabriel.devgram.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
