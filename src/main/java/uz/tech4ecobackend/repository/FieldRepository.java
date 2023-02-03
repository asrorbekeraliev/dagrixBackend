package uz.tech4ecobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tech4ecobackend.entity.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {
    public void deleteById(Long Id);
}
