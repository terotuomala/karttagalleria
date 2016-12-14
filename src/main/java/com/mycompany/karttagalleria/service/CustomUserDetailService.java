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
    AccountRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName())));
    }
}
