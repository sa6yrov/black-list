package kg.weare.blacklist.repository;

import kg.weare.blacklist.entity.BadGuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadGuyRepository extends JpaRepository<BadGuyEntity, Long> {
}
