package uz.tech4ecobackend.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.entity.Parameter;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    @Modifying
    void deleteById(Long Id);
    boolean existsNodeById(Long Id);

    @Modifying
    @Query(value = "DELETE from qurilmalar q where q.field_id=?1", nativeQuery = true)
    void deleteAllByFieldId(Long field_id);



}
