package com.mycompany.karttagalleria.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Tuomala
 * @version 1.0
 */

public class AccountTest {
    
    Account account;
    Role role;
    
    @Before
    public void setUp() {
        account = new Account();
        role = new Role();
    }

     @Test
     public void setUsername() {
         account.setUsername("Testikäyttäjätunnus");
         assertEquals("Testikäyttäjätunnus", account.getUsername());
     }
     
     @Test
     public void setPassword() {
         account.setPassword("Testisalasana");
         assertEquals("Testisalasana", account.getPassword());
     }
     
     @Test
     public void setRole() {
         role.setName("Testirooli");
         account.setRole(role);
         assertEquals("Testirooli", account.getRole().getName());
     }
}
