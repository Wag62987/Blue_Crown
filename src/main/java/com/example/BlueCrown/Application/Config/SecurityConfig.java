package com.example.BlueCrown.Application.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Autowired
    // @Lazy
    // CustomerDetails customerDetails;
    @Autowired
   CustomSuccesHandler customSuccessHandler;
//    @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//     auth.userDetailsService(customerDetails).passwordEncoder(passwordEncoder());
// }

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                 
                                .requestMatchers("/Admin/auth", "/css/**", "/js/**", "/images/**","/favicon/**").permitAll()
                                .requestMatchers("/Admin/**").hasAuthority("ROLE_Admin")
                                .requestMatchers("/User/**").hasAuthority("ROLE_User")
                                .anyRequest().authenticated()
                                
                )
                .formLogin(form -> form
                                .loginPage("/User/auth")
                                .loginProcessingUrl("/login")
                                .successHandler(customSuccessHandler)
                                .permitAll()
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/User/auth")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                )
               .sessionManagement(session -> session
    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    .maximumSessions(1)
    .maxSessionsPreventsLogin(true)
    .sessionRegistry(sessionRegistry())

                );

        return http.build();
    }

      @Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
}
@Bean
public static HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
}
}
