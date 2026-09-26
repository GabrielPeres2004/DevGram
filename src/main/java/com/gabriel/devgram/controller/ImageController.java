package com.gabriel.devgram.controller;


import com.gabriel.devgram.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/users/{filename}")
    public ResponseEntity<Resource> serveUserImage(@PathVariable String filename) {
        Resource resource = fileStorageService.load(filename, "users");

        return ResponseEntity.ok().body(resource);
    }

    @GetMapping(value = "/posts/{filename}")
    public ResponseEntity<Resource> servePostImage(@PathVariable String filename) {
        Resource resource = fileStorageService.load(filename, "posts");
        return ResponseEntity.ok().body(resource);
    }


}
