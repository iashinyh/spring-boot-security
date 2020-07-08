package inv.iashinyh.security.auth.repository;

import inv.iashinyh.security.auth.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    Stream<AuthorityEntity> findByUsername(String username);
}
