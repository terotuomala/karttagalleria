package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Role;
import com.mycompany.karttagalleria.domain.User;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.repository.UserRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

/**
 *
 * @author Tero Tuomala
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/addUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void modelHasAttributeMap() throws Exception {
        mockMvc.perform(get("/addUser"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    public void testSaveUser() throws Exception {
        
        userRepository.deleteAll();
        roleRepository.deleteAll();
        
        User user = new User();
        user.setUsername("usertesti");
        user.setPassword("salasana1234");
        
        Role role = new Role();
        role.setName("TESTIROOLI");
        
        user.setRole(role);
        
        roleRepository.save(role);
        userRepository.save(user);
        
        User retreived = userRepository.findByUsername("usertesti");
        assertNotNull(retreived);
        assertEquals("salasana1234", retreived.getPassword());
        assertEquals("TESTIROOLI", retreived.getRole().getName());
        
    }
}
