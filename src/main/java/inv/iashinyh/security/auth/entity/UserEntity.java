package inv.iashinyh.security.auth.entity;

import inv.iashinyh.security.auth.entity.converter.BooleanToYNConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Getter
@ToString
public class UserEntity extends SystemEntity {
    private String username;
    private String password;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean enabled;
}
