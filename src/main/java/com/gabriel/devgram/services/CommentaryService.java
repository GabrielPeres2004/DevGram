package com.gabriel.devgram.services;

import com.gabriel.devgram.domain.Commentary;
import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.dtos.request.CommentaryRequestDTO;
import com.gabriel.devgram.repositories.CommentaryRepository;
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
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    public List<Commentary> findAll() {
        return commentaryRepository.findAll();
    }

    public Commentary findById(Long id) {
        Optional<Commentary> commentary = commentaryRepository.findById(id);

        return commentary.orElseThrow( () -> new ObjectNotFoundException("Comentário não encontrado.") );

    }

    public List<Commentary> findByPostId(Long postId) {
        return commentaryRepository.findByPostId(postId);
    }

    public List<Commentary> findByUserId(Long userId) {
        return commentaryRepository.findByUserId(userId);
    }


    public Commentary create(Long postId, CommentaryRequestDTO commentaryRequestDTO) {
        Post post = postService.findById(postId);

        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();

        User user = userService.findById(userId);

        Commentary commentary = new Commentary();

        commentary.setPost(post);
        commentary.setUser(user);
        commentary.setContent(commentaryRequestDTO.getContent());
        commentary.setEdited(false);

        return commentaryRepository.save(commentary);
    }

    public Commentary update(Long id, CommentaryRequestDTO commentaryRequestDTO) {
        Commentary commentary = findById(id);
        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();

        if (!commentary.getUser().getId().equals(userId)) {
            throw new DataIntegrityViolationException("Você não tem permissão para editar este comentario.");
        }

        commentary.setContent(commentaryRequestDTO.getContent());
        commentary.setEdited(true);
        commentary.setUpdatedAt(LocalDateTime.now());

        return commentaryRepository.save(commentary);
    }

    public void delete(Long id) {
        Commentary commentary = findById(id);
        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userSS.getId();

        if (!commentary.getUser().getId().equals(userId)) {
            throw new DataIntegrityViolationException("Você não tem permissão para deletar este comentário.");
        }

        commentaryRepository.delete(commentary);
    }
}
