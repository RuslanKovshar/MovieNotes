package ruslan.kovshar.mmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ruslan.kovshar.mmdb.model.Roles;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.model.UserRole;
import ruslan.kovshar.mmdb.repository.UserRepository;
import ruslan.kovshar.mmdb.repository.UserRoleRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        UserRole userRole = userRoleRepository.findByRole(Roles.ROLE_USER)
                .orElseGet(() -> {
                    UserRole userRole1 = new UserRole();
                    userRole1.setId(1L);
                    userRole1.setRole(Roles.ROLE_USER);
                    return userRoleRepository.save(userRole1);
                });

        user.setRoles(Collections.singletonList(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
