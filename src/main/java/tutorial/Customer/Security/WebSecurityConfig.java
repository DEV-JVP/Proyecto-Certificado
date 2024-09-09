package tutorial.Customer.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/usuario",
                        "/findUser", "/certificado/{id}/pdf" ,"/css/**", "/js/**", "/images/**", "/libs/**","/scss/**","/busqueda").permitAll()  // Permite el acceso a la ruta principal y /home sin autenticación
                .requestMatchers("/index").authenticated()  // Requiere autenticación para estas rutas


                .anyRequest().authenticated()).formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/index", true).permitAll()).logout((logout) -> logout.logoutUrl("/logout")  // Configura la URL de logout
                .logoutSuccessUrl("/home")  // Redirige a la página de login después del logout
                .permitAll());

        return http.build();
    }
}
