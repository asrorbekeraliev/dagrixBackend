package uz.tech4ecobackend.service;

import org.springframework.stereotype.Service;
import uz.tech4ecobackend.entity.Field;
import uz.tech4ecobackend.repository.FieldRepository;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field create(Field field){
        Field result = fieldRepository.save(field);
        return result;
    }
}
