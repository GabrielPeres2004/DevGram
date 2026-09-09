package com.gabriel.devgram.controller;

import com.gabriel.devgram.domain.Commentary;
import com.gabriel.devgram.dtos.request.CommentaryRequestDTO;
import com.gabriel.devgram.dtos.response.CommentaryResponseDTO;
import com.gabriel.devgram.repositories.CommentaryRepository;
import com.gabriel.devgram.services.CommentaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/commentaries")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;


    @GetMapping()
    public ResponseEntity<List<CommentaryResponseDTO>> findAll() {
        List<Commentary> commentaries = commentaryService.findAll();
        List<CommentaryResponseDTO> commentaryDTO = commentaries.stream().map(CommentaryResponseDTO:: new).collect(Collectors.toList());

        return ResponseEntity.ok().body(commentaryDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentaryResponseDTO> findById(@PathVariable Long id) {
        Commentary commentary = commentaryService.findById(id);

        return ResponseEntity.ok().body(new CommentaryResponseDTO(commentary));
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<CommentaryResponseDTO>> findByPostId(@PathVariable Long postId) {
        List<Commentary> commentary = commentaryService.findByPostId(postId);
        List<CommentaryResponseDTO>  commentaryDTO = commentary.stream().map(CommentaryResponseDTO:: new).collect(Collectors.toList());

        return ResponseEntity.ok().body(commentaryDTO);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<CommentaryResponseDTO>> findByUserId(@PathVariable Long userId) {
        List<Commentary> commentary = commentaryService.findByUserId(userId);
        List<CommentaryResponseDTO>  commentaryDTO = commentary.stream().map(CommentaryResponseDTO:: new).collect(Collectors.toList());

        return ResponseEntity.ok().body(commentaryDTO);
    }

    @PostMapping(value = "/{postId}")
    public ResponseEntity<CommentaryResponseDTO> create(@PathVariable Long postId,@Valid @RequestBody CommentaryRequestDTO commentaryRequestDTO) {
        Commentary commentary = commentaryService.create(postId,commentaryRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(commentary.getId()).toUri();


        return ResponseEntity.created(uri).body(new CommentaryResponseDTO(commentary));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentaryResponseDTO> update(@PathVariable Long id, @RequestBody CommentaryRequestDTO commentaryRequestDTO) {
        Commentary commentary = commentaryService.update(id, commentaryRequestDTO);

        return ResponseEntity.ok().body(new CommentaryResponseDTO(commentary));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentaryService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
