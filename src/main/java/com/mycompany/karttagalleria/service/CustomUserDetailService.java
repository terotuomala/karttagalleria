package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Account;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mycompany.karttagalleria.repository.AccountRepository;

/**
 * CustomUserDetailService.java - a class for retrieving user from database
 * @author Tero Tuomala
 * @version 1.0
 */

@Service
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    AccountRepository accountRepository;
    
    // Searches Account object based on 'username', if found creates new 'Account' domain object from it
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + account.getRole().getName())));
        }
}
