package com.mycompany.karttagalleria.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Tuomala
 * @version 1.0
 */

public class RoleTest {
    
    Role role;
    Account account;
    
    @Before
    public void setUp() {
        role = new Role();
        account = new Account();
    }
    
     @Test
     public void setName() {
         role.setName("Testirooli");
         assertEquals("Testirooli", role.getName());
     }
     
     @Test
     public void setUsers() {
         account.setUsername("Testikäyttäjätunnus");
         role.setUsers(account);
         assertEquals("Testikäyttäjätunnus", role.getUsers().get(0).getUsername());
     }
}
