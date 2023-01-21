package uz.tech4ecobackend.service;

import org.springframework.transaction.annotation.Transactional;
import uz.tech4ecobackend.entity.User;
import uz.tech4ecobackend.entity.dto.UserDTO;
import uz.tech4ecobackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }
    @Transactional(readOnly = true)
    public UserDTO findByUser(String login){
        User user = userRepository.findByLogin(login);
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLastName(), user.getPassword(), user.getLogin(), user.getEmail(), user.getDateOfBirth(), user.getRoles());
        return userDTO;
    }
    @Transactional(readOnly = true)
    public List<User> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @Transactional(readOnly = true)
    public List<String> findAllLogins(){
        List<String> logins = userRepository.findAllLogins();
        return logins;
    }

    @Transactional(readOnly = true)
    public int numberOfUsers(){
        int numberOfUsers = userRepository.findAllLogins().size();
        return numberOfUsers;
    }


    public String deleteUserById(Long id){
        String result="";
        try {
            userRepository.deleteUserById(id);
        } catch (Exception exception){
            result = exception.getMessage();
        }
        userRepository.deleteById(id);
        return result;
    }





}
