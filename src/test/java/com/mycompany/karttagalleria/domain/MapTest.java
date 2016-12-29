package com.mycompany.karttagalleria.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Tuomala
 * @version 1.0
 */

public class MapTest {
    
    Map map;
    Category category;
    CoordinateSystem coordinateSystem;
    
    @Before
    public void setUp() {
        map = new Map();
        category = new Category();
        coordinateSystem = new CoordinateSystem();
    }

     @Test
     public void setTitle() {
         map.setTitle("Testiotsikko");
         assertEquals("Testiotsikko", map.getTitle());
     }
     
     @Test
     public void setCategory() {
         category.setName("Testikategoria");
         map.setCategory(category);
         assertEquals("Testikategoria", map.getCategory().getName());
     }
     
     @Test
     public void setDescription() {
         map.setDescription("Testikuvaus");
         assertEquals("Testikuvaus", map.getDescription());
     }
     
     @Test
     public void setCoordinateSystem() {
         coordinateSystem.setName("Testikoordinaatisto");
         map.setCoordinateSystem(coordinateSystem);
         assertEquals("Testikoordinaatisto", map.getCoordinateSystem().getName());
     }
     
     @Test
     public void setUrl() {
         map.setUrl("Testiurl");
         assertEquals("Testiurl", map.getUrl());
     }
}
