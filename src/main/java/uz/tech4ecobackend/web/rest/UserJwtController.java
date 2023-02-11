package uz.tech4ecobackend.web.rest;


import lombok.extern.slf4j.Slf4j;
import uz.tech4ecobackend.entity.dto.UserDTO;
import uz.tech4ecobackend.security.JwtTokenProvider;
import uz.tech4ecobackend.service.FieldService;
import uz.tech4ecobackend.service.NodeService;
import uz.tech4ecobackend.service.UserService;
import uz.tech4ecobackend.web.rest.loginVM.LoginVM;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://dottore.uz/")
@RestController
@RequestMapping("/api")
@Slf4j
public class UserJwtController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;
    private final NodeService nodeService;

    private final FieldService fieldService;

    public UserJwtController(JwtTokenProvider jwtTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, NodeService nodeService, FieldService fieldService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.nodeService = nodeService;
        this.fieldService = fieldService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM) {
        log.info("login: " + loginVM.getLogin());
        log.info("password: " + loginVM.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getLogin(), loginVM.getPassword());
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.createToken(loginVM.getLogin(), authentication);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt);
            UserDTO user = userService.findByUser(loginVM.getLogin());
            int numberOfUsers = userService.numberOfUsers();
            int numberOfDevices = nodeService.findAllNodeIDs().size();
            Map<String, String> body = new HashMap<>();
            body.put("success", "true");
            body.put("idToken", jwt);
            body.put("name", user.getName());
            body.put("lastname", user.getLastName());
            body.put("email", user.getEmail());
            body.put("login", user.getLogin());
            body.put("users", String.valueOf(numberOfUsers));
            body.put("devices", String.valueOf(numberOfDevices));
            body.put("fields", String.valueOf(fieldService.numberOfFields()));
            body.put("loggedIn", String.valueOf(true));
            log.info("User successfully signed in");
            return new ResponseEntity(body, headers, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("User not found in the database");
            Map<String, String> body = new HashMap<>();
            body.put("success", "false");
            return new ResponseEntity(body, HttpStatus.OK);
        }


    }

    @GetMapping("/getUDF") // UDF -> Users Devices Fields
    public ResponseEntity getUDF(){
        Map<String, String> UDF = new HashMap<>();
        UDF.put("fields", String.valueOf(fieldService.numberOfFields()));
        UDF.put("users", String.valueOf(userService.numberOfUsers()));
        UDF.put("devices", String.valueOf(nodeService.findAllNodeIDs().size()));
        return ResponseEntity.ok(UDF);
    }

}