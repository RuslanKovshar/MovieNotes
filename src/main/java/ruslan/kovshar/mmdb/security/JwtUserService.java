package ruslan.kovshar.mmdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.security.jwt.JwtUserFactory;
import ruslan.kovshar.mmdb.service.UserService;

@Service
public class JwtUserService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username " + username + " not found!"));

        return JwtUserFactory.create(user);
    }
}
