package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.CoordinateSystem;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import com.mycompany.karttagalleria.service.MapService;
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
 * @version 1.0
 * @since 27.12.2016
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;
    
    @Autowired
    MapService mapService;

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
    public void modelHasAttributeAdd() throws Exception {
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
    
    @Test
    public void redirectAfterEdit() throws Exception {
        Map map = new Map();
        map.setTitle("Karttasovellustesti1");

        Category cg = new Category();
        cg.setName("Kategoriatesti1");

        map.setCategory(cg);
        map.setDescription("Kuvaustesti1");

        CoordinateSystem cs = new CoordinateSystem();
        cs.setName("Koordinaatistotesti1");

        map.setCoordinateSystem(cs);
        map.setUrl("http://www.example.com");

        categoryRepository.save(cg);
        coordinateSystemRepository.save(cs);
        mapService.saveMap(map);
        
        mockMvc.perform(post("/map/edit/" + map.getId())
                .param("title", "Karttasovellustesti3")
                .param("category", "2")
                .param("description", "Kuvaustesti3")
                .param("coordinateSystem", "2")
                .param("url", "http://www.example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/gallery"));
    }
    
    @Test
    public void modelHasAttributeEdit() throws Exception {
        Map map = new Map();
        map.setTitle("Karttasovellustesti4");

        Category cg = new Category();
        cg.setName("Kategoriatesti4");

        map.setCategory(cg);
        map.setDescription("Kuvaustesti4");

        CoordinateSystem cs = new CoordinateSystem();
        cs.setName("Koordinaatistotesti4");

        map.setCoordinateSystem(cs);
        map.setUrl("http://www.example.com");

        categoryRepository.save(cg);
        coordinateSystemRepository.save(cs);
        mapService.saveMap(map);
        
        mockMvc.perform(get("/map/edit/" + map.getId()))
//                .andExpect(model().attributeExists("map"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("coordinateSystems"));
    }
    
}
