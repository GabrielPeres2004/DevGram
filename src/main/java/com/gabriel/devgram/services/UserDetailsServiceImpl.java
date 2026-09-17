package com.gabriel.devgram.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.repositories.UserRepository;
import com.gabriel.devgram.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> obj = userRepository.findByEmail(email);

        if (obj.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }

        User user = obj.get();

        return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}