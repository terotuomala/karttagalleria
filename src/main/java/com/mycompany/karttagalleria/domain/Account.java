package com.mycompany.karttagalleria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Tero Tuomala
 * @version 1.0
 */

@Entity
public class Account extends AbstractPersistable<Long>{
    
    @NotBlank
    @Length(min = 3, max = 20)
    @Column(unique = true)
    private String username;
    
    @NotBlank
    @Length(min = 10, max = 80)
    private String password;
    
    @NotNull
    @ManyToOne
    private Role role;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
  
}
