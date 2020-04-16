package ruslan.kovshar.mmdb.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.model.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                true,
                mapToGrantedAuthority(user.getRoles())
        );
    }

    private static Collection<? extends GrantedAuthority> mapToGrantedAuthority(List<UserRole> userRoles) {
        return userRoles
                .stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                .collect(Collectors.toList());
    }
}
