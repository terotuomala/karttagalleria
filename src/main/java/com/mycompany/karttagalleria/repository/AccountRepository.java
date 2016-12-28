package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AccountRepository.java - interface for managing 'Account' domain object
 * @author Tero Tuomala
 * @version 1.0
 */

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Account findByUsername(String username);
}
