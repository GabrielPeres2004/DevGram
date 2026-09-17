package com.gabriel.devgram.services;


import com.gabriel.devgram.domain.User;
import com.gabriel.devgram.domain.enums.Role;
import com.gabriel.devgram.dtos.request.UserRequestDTO;
import com.gabriel.devgram.repositories.UserRepository;
import com.gabriel.devgram.security.UserSS;
import com.gabriel.devgram.services.exceptions.DataIntegrityViolationException;
import com.gabriel.devgram.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado") );
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow( () -> new ObjectNotFoundException("Usuário nao encontrado com este email.") );


    }

    public Boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    public User create(UserRequestDTO userRequestDTO) {
        Boolean existsUsername  = existsByUsername(userRequestDTO.getUsername());
        Boolean existsByEmail  = existsByEmail(userRequestDTO.getEmail());


        if(existsUsername){
            throw new DataIntegrityViolationException("Username já cadastrado no sistema.");
        }

        if(existsByEmail){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
        }



        User newUser = new User();
        newUser.setUsername(userRequestDTO.getUsername());
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setPassword(encoder.encode(userRequestDTO.getPassword()));
        newUser.setFullName(userRequestDTO.getFullName());
        newUser.setBio(userRequestDTO.getBio());
        newUser.setImageURL(userRequestDTO.getImageURL());
        newUser.setRole(Role.USER);


        return userRepository.save(newUser);
    }

    public User update(long id, UserRequestDTO userRequestDTO) {
        User user = this.findById(id);


        if(!encoder.matches(userRequestDTO.getPassword(), user.getPassword() )) {
            throw new DataIntegrityViolationException("Senha atual incorreta.");
        }

        if (!user.getUsername().equals(userRequestDTO.getUsername()) && existsByUsername(userRequestDTO.getUsername())) {
            throw new DataIntegrityViolationException("Username já cadastrado no sistema.");
        }

        if (!user.getEmail().equals(userRequestDTO.getEmail()) && existsByEmail(userRequestDTO.getEmail())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
        }

        user.setBio(userRequestDTO.getBio());
        user.setUpdatedAt(LocalDateTime.now());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(encoder.encode(userRequestDTO.getPassword()));
        user.setUsername(userRequestDTO.getUsername());
        user.setFullName(userRequestDTO.getFullName());


        return userRepository.save(user);

    }

    public void delete(Long id) {
        User userToDelete = this.findById(id);

        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = this.findById(userSS.getId());

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new DataIntegrityViolationException("Apenas administradores podem excluir usuários.");
        }

        if (loggedUser.getId().equals(userToDelete.getId())) {
            throw new DataIntegrityViolationException("Você não pode excluir sua própria conta.");
        }

        userRepository.delete(userToDelete);
    }


}
