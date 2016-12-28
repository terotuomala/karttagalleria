package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MapRepository.java - interface for managing 'Map' domain object
 * @author Tero Tuomala
 * @version 1.0
 */

public interface MapRepository extends JpaRepository<Map, Long>{
    
    Map findByTitle(String title);
    
}
