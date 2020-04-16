package ruslan.kovshar.mmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ruslan.kovshar.mmdb.dto.AuthenticationDto;
import ruslan.kovshar.mmdb.dto.CreateUserDto;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.security.jwt.JwtTokenProvider;
import ruslan.kovshar.mmdb.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserRestController(UserService userService,
                              AuthenticationManager authenticationManager,
                              JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = createUserDto.toUser();
        User registerUser = userService.register(user);
        HashMap<Object, Object> response = new HashMap<>();
        response.put("id", registerUser.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        String username = authenticationDto.getUsername();
        String password = authenticationDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

            String token = jwtTokenProvider.createToken(user);

            Map<String, String> response = new HashMap<>(1, 1);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
