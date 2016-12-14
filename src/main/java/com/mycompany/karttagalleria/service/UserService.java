package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.karttagalleria.repository.AccountRepository;

/**
 *
 * @author Tero Tuomala
 */

@Service
public class UserService {
    
    @Autowired
    AccountRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    public void saveUser(Account user) {
        
        Role role = roleRepository.findOne(user.getRole().getId());
        role.setUsers(user);
        
        userRepository.save(user);
        roleRepository.save(role);
        
    }
}
