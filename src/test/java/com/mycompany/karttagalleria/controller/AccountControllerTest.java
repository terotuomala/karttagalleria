package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.mycompany.karttagalleria.repository.AccountRepository;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
    public void modelHasAttributeAccountList() throws Exception {
        mockMvc.perform(get("/account/list"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    public void modelHasAttributeAccountAdd() throws Exception {
        mockMvc.perform(get("/account/add"))
                .andExpect(model().attributeExists("roles"));
    }

}
