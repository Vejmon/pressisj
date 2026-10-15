package no.vejmon.kunstner.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SecuritySchemes(value = {
        @SecurityScheme(
            name = "basicAuth",
            type = SecuritySchemeType.HTTP,
            scheme = "basic"
)})
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").authenticated()
                        .requestMatchers("/open/**").permitAll()
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/open/**");
                })
                .logout(withDefaults())
                .formLogin(withDefaults())
            .build();
    }
}
