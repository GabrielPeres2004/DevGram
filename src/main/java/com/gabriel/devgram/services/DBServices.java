package com.gabriel.devgram.services;

import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.domain.enums.Role;
import com.gabriel.devgram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DBServices {

    @Autowired
    private UserRepository userRepository;


    public void instanceDB(){
        User user1 = new User(null, "gabrielperes", "gabriel@email.com", "123456",
                "Gabriel Luis Ferreira Peres", " ", " ", Role.ADMIN,
                LocalDateTime.of(2024, 1, 10, 10, 30), LocalDateTime.of(2024, 1, 10, 10, 30));

        userRepository.saveAll(List.of(user1));
    }

}
