package tutorial.Customer.Service;

import tutorial.Customer.Dao.UserRepository;
import tutorial.Customer.Entity.Role;
import tutorial.Customer.Dao.RoleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tutorial.Customer.Entity.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {



        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final PasswordEncoder passwordEncoder;



        @Autowired
        public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public void createUser(String username, String rawPassword, String roleName) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(rawPassword)); // Codifica la contraseña

            Role userRole = roleRepository.findByName(roleName);
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);
        }



}

