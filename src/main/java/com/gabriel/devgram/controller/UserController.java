package com.gabriel.devgram.controller;


import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.dtos.request.UserRequestDTO;
import com.gabriel.devgram.dtos.response.UserResponseDTO;
import com.gabriel.devgram.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<User> listUser = userService.findAll();
        List<UserResponseDTO> listUserDTO = listUser.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listUserDTO);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        User user = userService.findById(id);

        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDTO> findByEmail(@RequestParam String email){
        User user = userService.findByEmail(email);

        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @GetMapping("/username/exists")
    public ResponseEntity<Boolean> existsByUsername(@RequestParam String username){
        Boolean existsUsername  = userService.existsByUsername(username);

        return ResponseEntity.ok().body(existsUsername);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = userService.create(userRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();



        return ResponseEntity.created(uri).body(new UserResponseDTO(user));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable long id){
        User user = userService.update(id, userRequestDTO);

        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        String message = userService.delete(id);

        return  ResponseEntity.ok().body(message);
    }




}
