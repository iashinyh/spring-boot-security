package inv.iashinyh.security;

import inv.iashinyh.security.auth.filter.JwtTokenProvider;
import inv.iashinyh.security.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplicationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        UserDetails userDetail = userService.loadUserByUsername(user.get("username"));

        if(!user.get("password").equals(userDetail.getPassword()))
            throw new RuntimeException();

        return jwtTokenProvider.createToken(userDetail.getUsername(), userDetail.getAuthorities());
    }
}
