package com.mycompany.karttagalleria.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero Tuomala
 * @version 1.0
 */

public class CoordinateSystemTest {
    
    CoordinateSystem coordinateSystem;
    Map map;
    
    @Before
    public void setUp() {
        coordinateSystem = new CoordinateSystem();
        map = new Map();
    }
    
     @Test
     public void setName() {
         coordinateSystem.setName("Testikoordinaatisto");
         assertEquals("Testikoordinaatisto", coordinateSystem.getName());
     }
     
     @Test
     public void setMaps() {
         map.setTitle("Testiotsikko");
         coordinateSystem.setMaps(map);
         assertEquals("Testiotsikko", coordinateSystem.getMaps().get(0).getTitle());
     }
}
