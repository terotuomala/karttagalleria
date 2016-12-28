package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.CoordinateSystem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CoordinateSystemRepository.java - interface for managing 'CoordinateSystem' domain object
 * @author Tero Tuomala
 * @version 1.0
 */

public interface CoordinateSystemRepository extends JpaRepository<CoordinateSystem, Long> {
    
    CoordinateSystem findByName(String name);
    
}
