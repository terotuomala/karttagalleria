package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.mycompany.karttagalleria.repository.AccountRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 *
 * @author Tero Tuomala
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/account/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void modelHasAttributeList() throws Exception {
        mockMvc.perform(get("/account/list"))
                .andExpect(model().attributeExists("accounts"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    public void modelHasAttributeAdd() throws Exception {
        mockMvc.perform(get("/account/add"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    public void redirectAfterPost() throws Exception {
        mockMvc.perform(post("/account/add")
                .param("username", "Testiuser1")
                .param("password", "salasana1234")
                .param("role", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/gallery"));
    }
    
    @Test
    public void redirectAfterEdit() throws Exception {
        Account account = new Account();
        account.setUsername("usertesti1");
        account.setPassword("salasana1234");
        
        Role role = new Role();
        role.setName("TESTIROOLI1");
        account.setRole(role);
        
        roleRepository.save(role);
        accountRepository.save(account);
        
        mockMvc.perform(post("/account/edit/" + account.getId())
                .param("username", "Testiuser2")
                .param("password", "salasana1234")
                .param("role", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/gallery"));
    }  
    
    @Test
    public void modelHasAttributeEdit() throws Exception {
        Account account = new Account();
        account.setUsername("usertesti3");
        account.setPassword("salasana1234");
        
        Role role = new Role();
        role.setName("TESTIROOLI3");
        account.setRole(role);
        
        roleRepository.save(role);
        accountRepository.save(account);
        
        mockMvc.perform(get("/account/edit/" + account.getId()))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("roles"));
    }
    
    

}
