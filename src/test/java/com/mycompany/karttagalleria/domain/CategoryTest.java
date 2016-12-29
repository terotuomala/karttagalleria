package com.mycompany.karttagalleria.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Tuomala
 * @version 1.0
 */

public class CategoryTest {
    
    Category category;
    Map map;
    
    @Before
    public void setUp() {
        category = new Category();
        map = new Map();
    }
    
     @Test
     public void setName() {
         category.setName("Testikategoria");
         assertEquals("Testikategoria", category.getName());
     }
     
     @Test
     public void setMaps() {
         map.setTitle("Testiotsikko");
         category.setMaps(map);
         assertEquals("Testiotsikko", category.getMaps().get(0).getTitle());
     }
}
