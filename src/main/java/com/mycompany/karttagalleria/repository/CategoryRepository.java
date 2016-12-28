package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository.java - interface for managing 'Category' domain object
 * @author Tero Tuomala
 * @version 1.0
 */

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Category findByName(String name);
}
