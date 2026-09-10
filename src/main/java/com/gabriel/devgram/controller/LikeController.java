package com.gabriel.devgram.controller;

import com.gabriel.devgram.domain.Like;
import com.gabriel.devgram.dtos.response.LikeResponseDTO;
import com.gabriel.devgram.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping
    public ResponseEntity<List<LikeResponseDTO>> findAll(){
        List<Like> like = likeService.findAll();
        List<LikeResponseDTO> likeResponseDTO = like.stream().map(LikeResponseDTO::new).toList();

        return  ResponseEntity.ok().body(likeResponseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LikeResponseDTO> findById(@PathVariable Long id){
        Like like = likeService.findById(id);

        return  ResponseEntity.ok().body(new  LikeResponseDTO(like));
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<LikeResponseDTO>> findByPostId(@PathVariable Long postId){
        List<Like> like = likeService.findByPostId(postId);
        List<LikeResponseDTO> likeResponseDTO = like.stream().map(LikeResponseDTO::new).toList();

        return  ResponseEntity.ok().body(likeResponseDTO);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<LikeResponseDTO>> findByUserId(@PathVariable Long userId){
        List<Like> like = likeService.findByUserId(userId);
        List<LikeResponseDTO> likeResponseDTO = like.stream().map(LikeResponseDTO::new).toList();

        return  ResponseEntity.ok().body(likeResponseDTO);
    }

    @PostMapping(value = "/{postId}")
    public ResponseEntity<LikeResponseDTO> create(@PathVariable Long postId){
        Like like = likeService.create(postId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(like.getId()).toUri();

        return  ResponseEntity.created(uri).body(new  LikeResponseDTO(like));
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId){
        likeService.delete(postId);

        return  ResponseEntity.noContent().build();
    }


}
