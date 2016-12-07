package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByUsername(String username);
}
