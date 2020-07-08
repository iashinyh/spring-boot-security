package inv.iashinyh.security.auth.repository;

import inv.iashinyh.security.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Stream<RoleEntity> findByUsername(String username);
}
