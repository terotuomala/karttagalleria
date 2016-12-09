package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
