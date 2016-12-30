package com.mycompany.karttagalleria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * DefaultSecurityConfiguration.java - a class for 'default' profile security configuration 
 * @author Tero Tuomala
 * @version 1.0
 */

@Profile("default")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class DefaultSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers(HttpMethod.GET, "/map/*").access("hasRole('ADMIN') or hasRole('PUBLISHER') or hasRole('USER')")
                .antMatchers("/map/*").access("hasRole('ADMIN') or hasRole('PUBLISHER')")
                .antMatchers("/account/*").access("hasRole('ADMIN')")
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
    }
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password1234").roles("USER");
        auth.inMemoryAuthentication().withUser("publisher").password("password1234").roles("PUBLISHER");
        auth.inMemoryAuthentication().withUser("admin").password("password1234").roles("ADMIN");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
}
