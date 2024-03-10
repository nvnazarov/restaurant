package kpo.restorant.services;

import kpo.restorant.models.Role;
import kpo.restorant.models.User;
import kpo.restorant.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        // create admin
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ADMIN);
            User admin = User.builder()
                    .email("admin@admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(roles)
                    .active(true)
                    .build();
            userRepository.save(admin);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(User user) {
        user.setActive(true);
        user.getRoles().add(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
