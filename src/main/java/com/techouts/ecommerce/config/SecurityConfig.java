package com.techouts.ecommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailService;

    public SecurityConfig(UserDetailsService userDetailService) {

        this.userDetailService = userDetailService;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf (csrf -> csrf.disable ())
                .authenticationProvider (authenticationProvider ())
                .authorizeHttpRequests (
                        auth -> auth
                                .requestMatchers ("/products", "/product/*", "/home", "/static/**").permitAll ()
                                .requestMatchers ("/account", "/cart", "/cart/*", "/order", "/order/*").authenticated ()
                                .requestMatchers ("/admin", "/admin/*").hasRole ("ADMIN")
                                .anyRequest ().permitAll()

                )
                .formLogin ((formLogin) ->
                        formLogin
                                .loginPage ("/login")
                                .loginProcessingUrl ("/login")
                                .usernameParameter ("email")
                                .passwordParameter ("password")
                                .defaultSuccessUrl ("/home")
                                .failureUrl ("/login?failed")
                                .permitAll ()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/denied"));

        return httpSecurity.build ();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider ();
        daoAuthenticationProvider.setUserDetailsService (userDetailService);
        daoAuthenticationProvider.setPasswordEncoder (passwordEncoder());

        return daoAuthenticationProvider;
    }

}

