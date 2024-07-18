package com.microservice.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("*").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/v1/auth/**",
                "/swagger-resources/**",
                "/swagger-ui.html/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/v3/api-docs/**");
    }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(@NonNull CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                     .allowedMethods("*")
    //                     .allowedOrigins("http://localhost:3000", "http://localhost:3033")
    //                     .allowedHeaders("*")
    //                     .allowCredentials(true);
    //         }
    //     };
    // }
}
