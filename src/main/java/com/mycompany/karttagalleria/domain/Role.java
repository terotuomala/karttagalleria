package com.mycompany.karttagalleria.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Tero Tuomala
 */
@Entity
public class Role extends AbstractPersistable<Long> {
    
    @NotBlank
    @Column(unique = true)
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
}
