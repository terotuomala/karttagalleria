package com.mycompany.karttagalleria.repository;

import com.mycompany.karttagalleria.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tero Tuomala
 */

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Account findByUsername(String username);
}
