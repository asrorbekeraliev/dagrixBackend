package uz.tech4ecobackend.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tech4ecobackend.entity.Field;
import uz.tech4ecobackend.service.FieldService;

@RestController
@RequestMapping("/api")
public class FieldResource {
    private final FieldService fieldService;

    public FieldResource(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping("/field")
    public ResponseEntity create(@RequestBody Field field){
        return ResponseEntity.ok(fieldService.create(field));
    }
}
