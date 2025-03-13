package com.Basic_Authentication.Basic_Authentication1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*

     *By default, Spring Security enables both form-based
     * and HTTP basic authentication.
     * Here we are using httpBasic() element
     * to define only Basic Authentication inside
     * the SecurityFilterChain bean

     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeHttpRequests((authorize)->{
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1= User.builder()
                .username("user1")
                .password(passwordEncoder().encode("user123pwd"))
                .roles("USER")
                .build();

        UserDetails admin1=User.builder()
                .username("admin1")
                .password(passwordEncoder().encode("admin123pwd"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,admin1);
    }

    /*

     * InMemoryUserDetailsManager is a built-in class
     * in Spring Security that stores user information
     * in memory instead of a database.
     * It is mainly used for testing authentication quickly
     * without setting up a real database.

     */
}
