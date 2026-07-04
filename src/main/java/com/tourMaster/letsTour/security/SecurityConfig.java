package com.tourMaster.letsTour.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login.html").permitAll().
                        requestMatchers("/Registration.html").permitAll().requestMatchers("/uploads/logOffImages/regImage.jpg").permitAll()
                                .requestMatchers("/api/v1/register/**").permitAll().

                        requestMatchers("api/v1/tourDestinations/**").hasRole("USER")
                                .anyRequest().permitAll()

                );



        http.formLogin(fL -> fL.loginPage("/login").loginProcessingUrl("/doLogin").defaultSuccessUrl("/mainPage.html", true).failureUrl("/login.html?error=true").permitAll());
        http.logout(lOut -> {
            lOut.invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
        });
        return http.build();
    }



    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
   AuthenticationManagerBuilder authenticationManagerBuilder= http.getSharedObject(AuthenticationManagerBuilder.class);
          authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                  .passwordEncoder(new BCryptPasswordEncoder());
          return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
