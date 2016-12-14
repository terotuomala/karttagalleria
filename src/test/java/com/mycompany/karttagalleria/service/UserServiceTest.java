package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.domain.User;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.repository.UserRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Tero Tuomala
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserService userService;
    
    @Test
    public void testSaveUser() throws Exception {
        
        User user = new User();
        user.setUsername("usertesti");
        user.setPassword("salasana1234");
        
        Role role = new Role();
        role.setName("TESTIROOLI");
        user.setRole(role);
        
        roleRepository.save(role);
        System.out.println(user.getRole().getId());
        userService.saveUser(user);
        
        User retreivedUser = userRepository.findByUsername("usertesti");
        assertNotNull(retreivedUser);
        assertEquals("salasana1234", retreivedUser.getPassword());
        assertEquals("TESTIROOLI", retreivedUser.getRole().getName());
        
        Role retreivedRole = roleRepository.findByName("TESTIROOLI");
        assertNotNull(retreivedRole);
        assertEquals("usertesti", retreivedRole.getUsers().get(0).getUsername());
        
    }
}
