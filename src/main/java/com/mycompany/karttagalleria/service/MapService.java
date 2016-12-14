package com.mycompany.karttagalleria.service;

import com.mycompany.karttagalleria.domain.Category;
import com.mycompany.karttagalleria.domain.CoordinateSystem;
import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tero Tuomala
 */

@Service
public class MapService {
    
    @Autowired
    MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;
    
    @Transactional
    public void saveMap(Map map) {
        Category category = categoryRepository.findOne(map.getCategory().getId());
        CoordinateSystem coordinateSystem = coordinateSystemRepository.findOne(map.getCoordinateSystem().getId());

        category.setMaps(map);
        coordinateSystem.setMaps(map);
        
        mapRepository.save(map);
        categoryRepository.save(category);
        coordinateSystemRepository.save(coordinateSystem);
    }
}
