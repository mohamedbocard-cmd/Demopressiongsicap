package sn.pressingsicap.Demo.Security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sn.pressingsicap.Demo.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity /// pour securiser ton app avc  il faut activer @preauthorise.
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider(userDetailsService))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/static/css/**",
                                "/static/js/**",
                                "/images/**",
                                "/static/fonts/**",
                                "/logout",
                                "/static/vendor/**",
                                "/login/**",
                                "/inscription/**",
                                "/public/**",
                                "/static/scss/**",
                                "/favicon.ico"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/commandes/liste").permitAll()

                        .requestMatchers(HttpMethod.POST, "/admin/liste").permitAll()

                        .requestMatchers(HttpMethod.POST, "/clients/liste").permitAll()

                        .requestMatchers(HttpMethod.POST, "/articles/liste").permitAll()

                        .requestMatchers(HttpMethod.POST, "/paiements/liste").permitAll()

                        .requestMatchers(HttpMethod.POST, "/categories/liste").permitAll()
                        .requestMatchers(HttpMethod.POST, "/factures/liste").permitAll()


                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN","GERANT","EMPLOYER")

                        .requestMatchers("/employe/**").hasAnyRole("EMPLOYER")


                        .requestMatchers("/utilisateur/**").hasRole("ADMIN")
                        .requestMatchers("/paiements/**").hasAnyRole("ADMIN", "GERANT")

                        .requestMatchers("/commandes/**").hasAnyRole("ADMIN", "GERANT")

                        .requestMatchers("/categories/**").hasAnyRole("ADMIN", "GERANT")



                        .requestMatchers("/clients/ajouter/**").hasAnyRole("GERANT", "ADMIN")
                        .requestMatchers("/clients/liste/**").hasAnyRole("GERANT", "ADMIN")


                        .requestMatchers(HttpMethod.POST, "/clients/delete/**")
                        .hasAnyRole("GERANT", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/articles/delete/**")
                        .hasAnyRole("GERANT", "ADMIN","EMPLOYER")

                        .requestMatchers(HttpMethod.POST, "/paiements/liste").hasAnyRole("ADMIN", "GERANT")

                         .requestMatchers("/commandes/save/**").hasAnyRole("GERANT", "ADMIN")

                        .requestMatchers("/commandes/liste/**").hasAnyRole("GERANT", "ADMIN")

                        .requestMatchers(HttpMethod.POST,"/articles/liste/**").hasAnyRole("EMPLOYER","ADMIN", "GERANT")
                        .requestMatchers(HttpMethod.POST,"/factures/liste/**").hasAnyRole("ADMIN", "GERANT")

                        .requestMatchers(HttpMethod.POST,"/factures/ajouter/**").hasAnyRole("GERANT", "ADMIN")
                        .requestMatchers(HttpMethod.POST,"/factures/save/**").hasAnyRole("GERANT", "ADMIN")


                        .requestMatchers(HttpMethod.POST,"/inscription/**").permitAll()
                        .anyRequest().authenticated()

                )
                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))

                .formLogin(form -> form
                        .loginPage("/login") //form ->Personnalisation
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    /* POUT IDENTIFIER LES USERS PAR LEUR USERNAME & password*/
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();

    }



}
