package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.karttagalleria.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Tero Tuomala
 */

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    
    public void saveUser(Account account) {
        
        Role role = roleRepository.findOne(account.getRole().getId());
        role.setUsers(account);
        
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        accountRepository.save(account);
        roleRepository.save(role);
        
    }
}
