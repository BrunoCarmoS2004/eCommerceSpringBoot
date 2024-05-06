package com.br.eCormmerce.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Autowired
  SecurityFilter securityFilter;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    return httpSecurity.csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    
    .authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/vendedor/**").hasRole("VENDEDOR")
    .requestMatchers("/admin/**").hasRole("ADMIN")
    .requestMatchers("/cliente/**").hasRole("CLIENTE")
    .requestMatchers("/produto/**").hasRole("CLIENTE")
    .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
    .requestMatchers(HttpMethod.GET,"/endereco/get").permitAll()
    .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
    .requestMatchers("/endereco/**").permitAll()
    .requestMatchers(HttpMethod.POST,"/admin/endereco/criar").permitAll()
    .anyRequest().authenticated())
    .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
                // Você pode adicionar qualquer manipulação adicional aqui após o logout bem-sucedido
                response.setStatus(HttpServletResponse.SC_OK);
            })
    )
    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
    .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
  }
  @Bean
  public PasswordEncoder PasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
