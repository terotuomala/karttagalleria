package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.repository.MapRepository;
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
 * GalleryControllerTest.java - a class for testing GalleryController
 * @author Tero Tuomala
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GalleryControllerTest {
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    MapRepository mapRepository;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/gallery"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void modelHasAttributeAircrafts() throws Exception {
        mockMvc.perform(get("/gallery"))
                .andExpect(model().attributeExists("maps"));
    }
    
    
}
