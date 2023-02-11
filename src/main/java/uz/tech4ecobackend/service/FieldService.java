package uz.tech4ecobackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tech4ecobackend.entity.Field;
import uz.tech4ecobackend.repository.FieldRepository;
import uz.tech4ecobackend.repository.NodeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;
    private final NodeRepository nodeRepository;

    public FieldService(FieldRepository fieldRepository, NodeRepository nodeRepository) {
        this.fieldRepository = fieldRepository;
        this.nodeRepository = nodeRepository;
    }

    public Field create(Field field){
        Field result = fieldRepository.save(field);
        return result;
    }
    @Transactional(readOnly = true)
    public List<Field> allFields(){
        List<Field> fields = new ArrayList<>();
        fields = fieldRepository.findAll();
        return fields;
    }

    public int numberOfFields(){
        return fieldRepository.findAll().stream().toList().size();
    }

    public int deleteField(Long id){
        try {
            nodeRepository.deleteAllByFieldId(id);
        } catch (Exception e){
            System.out.println("Mana exception: " + e);
        }
        fieldRepository.deleteById(id);
        return nodeRepository.findAll().size();  // number of devices is returning
    }
}
