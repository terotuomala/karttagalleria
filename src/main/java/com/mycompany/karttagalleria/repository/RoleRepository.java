package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository.java - interface for managing 'Role' domain object
 * @author Tero Tuomala
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
