package ruslan.kovshar.mmdb.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.security.jwt.JwtTokenProvider;
import ruslan.kovshar.mmdb.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserExtractor {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public UserExtractor(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    public User extract(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);

        return userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
