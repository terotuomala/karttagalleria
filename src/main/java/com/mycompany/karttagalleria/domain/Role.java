package com.mycompany.karttagalleria.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<Account> users;
    
    public Role() {
        this.users = new ArrayList<Account>();
    }
    
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
    public List<Account> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Account user) {
        this.users.add(user);
    }
    
}
