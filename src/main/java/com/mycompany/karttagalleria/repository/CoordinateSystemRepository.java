package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.CoordinateSystem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface CoordinateSystemRepository extends JpaRepository<CoordinateSystem, Long> {
    
    CoordinateSystem findByName(String name);
    
}
