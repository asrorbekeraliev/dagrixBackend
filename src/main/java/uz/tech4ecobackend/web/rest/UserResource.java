package uz.tech4ecobackend.web.rest;

import uz.tech4ecobackend.entity.Role;
import uz.tech4ecobackend.entity.User;
import uz.tech4ecobackend.entity.dto.UserDTO;
import uz.tech4ecobackend.security.SecurityUtils;
import uz.tech4ecobackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
//@CrossOrigin(origins = "http://dottore.uz/")
@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }



    @GetMapping("/account")
    public ResponseEntity<?> getAccount(){
        try {
            String login = SecurityUtils.getCurrentUserName().get();
            UserDTO user = userService.findByUser(login);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.ok(e);
        }

    }


    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/userbylogin/{login}")
    public ResponseEntity getUserByLogin(@PathVariable String login){
        UserDTO user = userService.findByUser(login);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logins")
    public ResponseEntity getUserLogins(){
        List<String> logins = userService.findAllLogins();
        return ResponseEntity.ok(logins);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        String result = userService.deleteUserById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }






}
