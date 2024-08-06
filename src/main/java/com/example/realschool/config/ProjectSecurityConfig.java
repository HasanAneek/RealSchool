package com.example.realschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers("/dashboard").authenticated()
                            .requestMatchers("/displayMessages").hasRole("ADMIN")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                            .requestMatchers("/displayProfile").authenticated()
                            .requestMatchers("/updateProfile").authenticated()
                            .requestMatchers("/student/**").hasRole("STUDENT")
                            .requestMatchers("/", "/home").permitAll()
                            .requestMatchers("/contact").permitAll()
                            .requestMatchers("/saveMsg").permitAll()
                            .requestMatchers("/courses").permitAll()
                            .requestMatchers("/holidays/**").permitAll()
                            .requestMatchers("/about").permitAll()
                            .requestMatchers("/assets/**").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/logout").permitAll()
                            .requestMatchers("/public/**").permitAll();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll())

                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers("/public/**")
                );

        return http.build();
    }

}
