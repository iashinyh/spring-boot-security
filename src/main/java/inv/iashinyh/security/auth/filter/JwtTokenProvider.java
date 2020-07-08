package inv.iashinyh.security.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private String secretKey = "test";
    private Long tokenValidTime = 60 * 60 * 1000L;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + tokenValidTime))
                    .signWith(SignatureAlgorithm.RS256, secretKey)
                    .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails user = userDetailsService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token) {
        Date expirationTime = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();

        return !(new Date()).before(expirationTime);
    }
}
