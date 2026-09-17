package com.gabriel.devgram.controller;

import com.gabriel.devgram.domain.Post;
import com.gabriel.devgram.domain.enums.Visibility;
import com.gabriel.devgram.dtos.request.PostRequestDTO;
import com.gabriel.devgram.dtos.request.PostUpdateRequestDTO;
import com.gabriel.devgram.dtos.response.PostResponseDTO;
import com.gabriel.devgram.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public ResponseEntity<List<PostResponseDTO>> findAll() {
        List<Post> posts = postService.findAll();
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(new PostResponseDTO(post));
    }

    @GetMapping("/title")
    public ResponseEntity<List<PostResponseDTO>> findByTitle(@RequestParam String title) {
        List<Post> posts = postService.findByTitle(title);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/content")
    public ResponseEntity<List<PostResponseDTO>> findByContent(@RequestParam String content) {
        List<Post> posts = postService.findByContent(content);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDTO>> findByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.findByUserId(userId);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<PostResponseDTO>> findByUserIdOrderByCreatedAtDesc(@PathVariable Long userId) {
        List<Post> posts = postService.findByUserIdOrderByCreatedAtDesc(userId);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/visibility")
    public ResponseEntity<List<PostResponseDTO>> findByVisibility(@RequestParam Visibility visibility) {
        List<Post> posts = postService.findByVisibility(visibility);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<PostResponseDTO>> findAllOrderByCreatedAtDesc() {
        List<Post> posts = postService.findAllOrderByCreatedAtDesc();
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/since")
    public ResponseEntity<List<PostResponseDTO>> findByCreatedAtAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data) {
        List<Post> posts = postService.findByCreatedAtAfter(data);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/location")
    public ResponseEntity<List<PostResponseDTO>> findByLocation(@RequestParam String location) {
        List<Post> posts = postService.findByLocation(location);
        List<PostResponseDTO> postsDTO = posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> countByUserId(@PathVariable Long userId) {
        long count = postService.countByUserId(userId);
        return ResponseEntity.ok().body(count);
    }


    @PostMapping()
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO postRequestDTO){
        Post post = postService.create(postRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(new PostResponseDTO(post));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PostUpdateRequestDTO postUpdateRequestDTO){
        Post post = postService.update(id, postUpdateRequestDTO);
        return ResponseEntity.ok().body(new PostResponseDTO(post));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

}