package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.repository.RoleRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mycompany.karttagalleria.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Tero Tuomala
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void testSaveUser() throws Exception {
        
        Account user = new Account();
        user.setUsername("usertesti");
        user.setPassword(passwordEncoder.encode("salasana1234"));
        
        Role role = new Role();
        role.setName("TESTIROOLI");
        user.setRole(role);
        
        roleRepository.save(role);
        System.out.println(user.getRole().getId());
        accountService.saveUser(user);
        
        Account retreivedUser = accountRepository.findByUsername("usertesti");
        assertNotNull(retreivedUser);
        assertEquals("salasana1234", retreivedUser.getPassword());
        assertEquals("TESTIROOLI", retreivedUser.getRole().getName());
        
        Role retreivedRole = roleRepository.findByName("TESTIROOLI");
        assertNotNull(retreivedRole);
        assertEquals("usertesti", retreivedRole.getUsers().get(0).getUsername());
        
    }
}
