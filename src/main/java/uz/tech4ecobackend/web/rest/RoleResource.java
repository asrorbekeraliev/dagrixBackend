package uz.tech4ecobackend.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tech4ecobackend.entity.Role;
import uz.tech4ecobackend.service.RoleService;
//@CrossOrigin(origins = "http://dottore.uz/")
@RestController
@RequestMapping("/api/role")

public class RoleResource {
    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity saveRole(@RequestBody Role role){
        Role result = roleService.create(role);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get")
    public ResponseEntity getRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
