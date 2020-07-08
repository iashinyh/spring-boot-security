package inv.iashinyh.security.auth.service;

import inv.iashinyh.security.auth.dto.UserDto;
import inv.iashinyh.security.auth.entity.RoleEntity;
import inv.iashinyh.security.auth.entity.UserEntity;
import inv.iashinyh.security.auth.repository.RoleRepository;
import inv.iashinyh.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository authorityRepository;

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByUsername(username);

        optional.orElseThrow(() -> {throw new UsernameNotFoundException(username);});

        UserEntity userEntity = optional.get();

        return UserDto.builder()
                        .username(userEntity.getUsername())
                        .password(userEntity.getPassword())
                        .roles(userEntity.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()))
                        .enabled(userEntity.isEnabled())
                        .build();
    }
//
//    public List<String> loadAuthoritiesByUsername(String username) throws UsernameNotFoundException {
//        Stream<RoleEntity> stream = authorityRepository.findByUsername(username);
//
//
//        stream.forEach(authorityEntity -> {;
//            list.add(new SimpleGrantedAuthority(authorityEntity.getAuthority()));
//        });
//
//        return stream.collect(Collectors.toList());
//    }


}
