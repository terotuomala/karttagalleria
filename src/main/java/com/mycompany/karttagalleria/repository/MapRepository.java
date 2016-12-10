package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface MapRepository extends JpaRepository<Map, Long>{
    
    Map findByTitle(String title);
}
