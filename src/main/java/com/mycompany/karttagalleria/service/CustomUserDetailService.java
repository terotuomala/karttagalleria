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
 *
 * @author Tero Tuomala
 */

@Service
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        System.out.println("TUNNUKSEN ROOLI: " + account.getRole().getName());
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
