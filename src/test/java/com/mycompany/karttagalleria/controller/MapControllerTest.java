package com.mycompany.karttagalleria.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Tero Tuomala
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/map/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void modelHasAttribute() throws Exception {
        mockMvc.perform(get("/map/add"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("coordinateSystems"));
    }
    
    @Test
    public void redirectAfterPost() throws Exception {
        mockMvc.perform(post("/map/add?reqType=post")
                .param("title", "Testikartta1")
                .param("category", "1")
                .param("description", "Kuvaustesti1")
                .param("coordinateSystem", "1")
                .param("url", "http://www.example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/gallery"));
    }
    
}
