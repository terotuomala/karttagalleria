package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Category findByName(String name);
}
