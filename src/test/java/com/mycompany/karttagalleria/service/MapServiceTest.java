package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.CoordinateSystem;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Tero Tuomala
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapServiceTest {

    @Autowired
    MapRepository mapRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;
    
    @Autowired
    MapService mapService;

    @Test
    public void testSaveMap() throws Exception {

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
        
        categoryRepository.save(cg);
        coordinateSystemRepository.save(cs);
        mapService.saveMap(map);

        Map retreivedMap = mapRepository.findByTitle("Karttasovellustesti");
        assertNotNull(retreivedMap);
        assertEquals("Kategoriatesti", retreivedMap.getCategory().getName());
        assertEquals("Kuvaustesti", retreivedMap.getDescription());
        assertEquals("Koordinaatistotesti", retreivedMap.getCoordinateSystem().getName());
        assertEquals("http://www.example.com", retreivedMap.getUrl());
        
        Category retreivedCategory = categoryRepository.findByName("Kategoriatesti");
        assertNotNull(retreivedCategory);
        assertEquals("Karttasovellustesti", retreivedCategory.getMaps().get(0).getTitle());
        
        CoordinateSystem retreivedCoordinateSystem = coordinateSystemRepository.findByName("Koordinaatistotesti");
        assertNotNull(retreivedCoordinateSystem);
        assertEquals("Karttasovellustesti", retreivedCoordinateSystem.getMaps().get(0).getTitle());
        
    }

}