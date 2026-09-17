package com.gabriel.devgram.services;

import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.domain.enums.Role;
import com.gabriel.devgram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBServices {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void instanceDB(){
        User user1 = new User(
                "admin",
                "admin@email.com",
                encoder.encode("123456"),
                "admin",
                " ",
                " "
        );

        user1.setRole(Role.USER);

        userRepository.save(user1);
    }

}