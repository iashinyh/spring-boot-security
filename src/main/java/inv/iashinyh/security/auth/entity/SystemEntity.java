package inv.iashinyh.security.auth.entity;


import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class SystemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat
    private LocalDateTime createAt;

    @DateTimeFormat
    private LocalDateTime updateAt;

    @Version
    private Long version;
}
