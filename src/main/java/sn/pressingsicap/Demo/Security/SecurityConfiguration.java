package sn.pressingsicap.Demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // Accès public
                        .requestMatchers(HttpMethod.GET,
                                "http://localhost:8165/api/clients/liste/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "http://localhost:8165/api/clients/enregistrer/**", "/", "/login", "/css/**", "/js/**")
                        .permitAll()

                        // Administration
                        .requestMatchers("/utilisateurs/**")
                        .hasRole("ADMIN")

                        // API protégée
                        .requestMatchers("/clients/**")
                        .hasAnyRole("ADMIN", "GERANT", "EMPLOYE")

                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest()
                        .authenticated()
                )

                .formLogin(Customizer.withDefaults())

                // Désactiver CSRF pour les tests REST API
                .csrf(csrf -> csrf.disable());

        return http.build();
    }}
