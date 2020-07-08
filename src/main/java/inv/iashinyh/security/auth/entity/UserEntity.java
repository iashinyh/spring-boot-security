package inv.iashinyh.security.auth.entity;

import inv.iashinyh.security.auth.entity.converter.BooleanToYNConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@ToString
public class UserEntity extends SystemEntity {
    private String username;
    private String password;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<RoleEntity> roles = new ArrayList<>();
}
