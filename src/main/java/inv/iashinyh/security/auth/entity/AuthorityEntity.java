package inv.iashinyh.security.auth.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
@Getter
@ToString
public class AuthorityEntity extends SystemEntity {
    private String username;
    private String authority;
}
