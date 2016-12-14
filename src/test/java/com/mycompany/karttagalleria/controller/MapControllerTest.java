package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.CoordinateSystem;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
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
public class MapControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    MapRepository mapRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/addMap"))
                .andExpect(status().isOk());
    }

    @Test
    public void modelHasAttributeMap() throws Exception {
        mockMvc.perform(get("/addMap"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("coordinateSystems"));
    }

    @Test
    public void testSaveMap() throws Exception {

        mapRepository.deleteAll();
        categoryRepository.deleteAll();
        coordinateSystemRepository.deleteAll();

        Map map = new Map();
        map.setTitle("Karttasovellustesti");

        Category cg = new Category();
        cg.setName("Kategoriatesti");

        map.setCategory(cg);
        map.setDescription("Kuvaustesti");

        CoordinateSystem cs = new CoordinateSystem();
        cs.setName("Koordinaatistotesti");

        map.setCoordinateSystem(cs);
        map.setUrl("http://www.example.com");

        coordinateSystemRepository.save(cs);
        categoryRepository.save(cg);
        mapRepository.save(map);

        Map retreived = mapRepository.findByTitle("Karttasovellustesti");
        assertNotNull(retreived);
        assertEquals("Kategoriatesti", retreived.getCategory().getName());
        assertEquals("Kuvaustesti", retreived.getDescription());
        assertEquals("Koordinaatistotesti", retreived.getCoordinateSystem().getName());
        assertEquals("http://www.example.com", retreived.getUrl());

    }
}