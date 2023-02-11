package uz.tech4ecobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tech4ecobackend.entity.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
FileStorage findByHashId(String hashId);
FileStorage findByLogin(String login);
}
