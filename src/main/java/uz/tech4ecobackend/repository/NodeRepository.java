package uz.tech4ecobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.entity.Parameter;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

    public void deleteById(Long Id);
    public boolean existsNodeById(Long Id);



}
