package uz.tech4ecobackend.repository;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.tech4ecobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    User findByLogin(String Login);

    @Query(value = "SELECT u.login from User u")
    List<String> findAllLogins();

    @Modifying
    @Query(value = "DELETE FROM user_role WHERE user_id = ?1", nativeQuery = true)
    void deleteUserById(Long id);




}
