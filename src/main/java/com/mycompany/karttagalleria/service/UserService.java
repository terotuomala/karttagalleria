package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.User;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tero Tuomala
 */

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    public void saveUser(User user) {
        
        Role role = roleRepository.findOne(user.getRole().getId());
        
        List<User> users = new ArrayList<User>();
        users.add(user);
        role.setUsers(users);
        
        userRepository.save(user);
        roleRepository.save(role);
        
        
    }
}
