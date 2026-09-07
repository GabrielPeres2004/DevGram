package com.gabriel.devgram.services;

import com.gabriel.devgram.repositories.CommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;
}
