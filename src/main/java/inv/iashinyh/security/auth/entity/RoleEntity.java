package inv.iashinyh.security.auth.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@ToString
public class RoleEntity extends SystemEntity {

    @Column(name = "username")
    private String username;
    private String role;
}
