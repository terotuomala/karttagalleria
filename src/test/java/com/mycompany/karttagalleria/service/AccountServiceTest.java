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
    
    
    @Test
    public void testSaveAccount() throws Exception {
        
        Account account = new Account();
        account.setUsername("usertesti");
        account.setPassword("salasana1234");
        
        Role role = new Role();
        role.setName("TESTIROOLI");
        account.setRole(role);
        
        roleRepository.save(role);
        accountRepository.save(account);
        
        Account retreivedUser = accountRepository.findByUsername("usertesti");
        assertNotNull(retreivedUser);
        assertEquals("salasana1234", retreivedUser.getPassword());
        assertEquals("TESTIROOLI", retreivedUser.getRole().getName());
        
        Role retreivedRole = roleRepository.findByName("TESTIROOLI");
        assertNotNull(retreivedRole);
        assertEquals("usertesti", retreivedRole.getUsers().get(0).getUsername());
        
    }
    
    @Test
    public void testUpdateAccount() throws Exception {
        
        Account newAccount = accountRepository.findByUsername("usertesti");
        newAccount.setUsername("usertesti2");
        newAccount.setPassword("salasana12345");
        
        Role newRole = roleRepository.findByName("TESTIROOLI");
        newRole.setName("TESTIROOLI2");
        newAccount.setRole(newRole);
        
        roleRepository.save(newRole);
        accountRepository.save(newAccount);
        
        
        Account retreivedUser = accountRepository.findByUsername("usertesti2");
        assertNotNull(retreivedUser);
        assertEquals("salasana12345", retreivedUser.getPassword());
        assertEquals("TESTIROOLI2", retreivedUser.getRole().getName());
        
        Role retreivedRole = roleRepository.findByName("TESTIROOLI2");
        assertNotNull(retreivedRole);
        assertEquals("usertesti2", retreivedRole.getUsers().get(0).getUsername());
    }
}
