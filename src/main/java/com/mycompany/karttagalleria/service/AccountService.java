package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.karttagalleria.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountService.java - a class for saving and updating 'Account' domain objects
 * @author Tero Tuomala
 * @version 1.0
 */

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    
    @Transactional
    public void saveAccount(Account account) {
        
        Role role = roleRepository.findOne(account.getRole().getId());
        role.setUsers(account);
        
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        accountRepository.save(account);
        roleRepository.save(role);
        
    }
    
    @Transactional
    public void updateAccount(Account oldAccount, Account newAccount) {
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        oldAccount.setRole(newAccount.getRole());
        
        Role role = roleRepository.findOne(oldAccount.getRole().getId());
        role.setUsers(oldAccount);  
        
        accountRepository.save(oldAccount);
        roleRepository.save(role);
        
    }
}
