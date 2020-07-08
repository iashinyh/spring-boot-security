package inv.iashinyh.security.auth.service;

import inv.iashinyh.security.auth.dto.UserDto;
import inv.iashinyh.security.auth.entity.AuthorityEntity;
import inv.iashinyh.security.auth.entity.UserEntity;
import inv.iashinyh.security.auth.repository.AuthorityRepository;
import inv.iashinyh.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByUsername(username);

        optional.orElseThrow(() -> {throw new UsernameNotFoundException(username);});

        UserEntity userEntity = optional.get();
        System.out.println(userEntity);
        return UserDto.builder()
                        .username(userEntity.getUsername())
                        .password(userEntity.getPassword())
                        .authorities(loadAuthoritiesByUsername(username))
                        .enabled(userEntity.isEnabled())
                        .build();
    }

    public List<GrantedAuthority> loadAuthoritiesByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> list = new ArrayList<>();

        Stream<AuthorityEntity> stream = authorityRepository.findByUsername(username);

        stream.forEach(authorityEntity -> {;
            list.add(new SimpleGrantedAuthority(authorityEntity.getAuthority()));
        });

        return list;
    }


}
